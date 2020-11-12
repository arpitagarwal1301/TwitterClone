package com.arpit.twitterclone.data.remoteapi

import com.arpit.twitterclone.model.TweetNetworkResponse
import retrofit2.Response
import retrofit2.http.GET

/**
 * Service to fetch Twitter posts using given api url.
 */
interface TwitterCloneService {

    @GET("/tweets")
    suspend fun getPosts(): Response<TweetNetworkResponse>

    companion object {
        const val TWITTER_API_URL = "https://6f8a2fec-1605-4dc7-a081-a8521fad389a.mock.pstmn.io"
    }
}
