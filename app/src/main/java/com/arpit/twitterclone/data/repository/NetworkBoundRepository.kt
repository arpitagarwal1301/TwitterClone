package com.arpit.twitterclone.data.repository

import androidx.annotation.WorkerThread
import com.arpit.twitterclone.model.State
import kotlinx.coroutines.flow.*
import retrofit2.Response

/**
 * Network bound repository
 *
 * @param ResultType
 * @param RequestType
 * @constructor Create empty Network bound repository
 */
abstract class NetworkBoundRepository<ResultType,RequestType> {

    fun asFlow() = flow<State<ResultType>> {

        // Emit Loading State
        emit(State.loading())

        // Emit Database content first
        emit(State.success(fetchFromLocal().first()))

        // Fetch latest posts from remote
        val apiResponse = fetchFromRemote()

        // Parse body
        val remotePosts = apiResponse.body()

        // Check for response validation
        if (apiResponse.isSuccessful && remotePosts != null) {
            // Save posts into the persistence storage
            deleteDbData()
            saveRemoteData(remotePosts)
        } else {
            // Something went wrong! Emit Error state.
            emit(State.error(apiResponse.message()))
        }

        // Retrieve posts from persistence storage and emit
        emitAll(
            fetchFromLocal().map {
                State.success(it)
            }
        )
    }.catch { e ->
        // Exception occurred! Emit error
        emit(State.error("Error in fetching data"))
        e.printStackTrace()
    }

    /**
     * Save remote data
     * @param response
     */
    @WorkerThread
    protected abstract suspend fun saveRemoteData(response: RequestType)

    @WorkerThread
    protected abstract suspend fun deleteDbData()

    /**
     * Fetch from local
     * @return
     */
    protected abstract fun fetchFromLocal(): Flow<ResultType>

    /**
     * Fetch from remote
     * @return
     */
    protected abstract suspend fun fetchFromRemote(): Response<RequestType>
}
