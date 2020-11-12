package com.arpit.twitterclone.data.repository

import androidx.annotation.MainThread
import com.arpit.twitterclone.data.local.dao.PostsDao
import com.arpit.twitterclone.data.remoteapi.TwitterCloneService
import com.arpit.twitterclone.model.Post
import com.arpit.twitterclone.model.State
import com.arpit.twitterclone.model.TweetNetworkResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Singleton repository for fetching data from remote and storing it in database
 * for offline capability.
 */
@Singleton
class PostsRepository @Inject constructor(
    private val postsDao: PostsDao,
    private val twitterDemoService: TwitterCloneService
) {


    /**
     * Fetched the posts from network and stored it in database. At the end, data from persistence
     * storage is fetched and emitted.
     */
    fun getAllPosts(): Flow<State<List<Post>>> {
        return object : NetworkBoundRepository<List<Post>, TweetNetworkResponse>() {

            override suspend fun saveRemoteData(response: TweetNetworkResponse) =
                postsDao.insertPosts(response.data)

            override fun fetchFromLocal(): Flow<List<Post>> = postsDao.getAllPosts()

            override suspend fun fetchFromRemote(): Response<TweetNetworkResponse> = twitterDemoService.getPosts()
        }.asFlow()
    }

    /**
     * Get post by search val
     * searchText - Search text
     * returns List of Posts
     */
    @MainThread
    fun getPostBySearchVal(searchText: String): Flow<List<Post>> = postsDao.getPostBySearchVal(searchText)

}
