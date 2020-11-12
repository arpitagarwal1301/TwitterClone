package com.arpit.twitterclone.data.repository

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

            override suspend fun saveRemoteData(response: TweetNetworkResponse) {
                val list = response.data
                var listPost = mutableListOf<Post>()

                for (i in list){
                    listPost.add(Post(
                        name = i.name,
                        handle = i.handle,
                        favoriteCount = i.favoriteCount,
                        retweetCount = i.retweetCount,
                        profileImageUrl = i.profileImageUrl,
                        text = i.text
                    ))
                }

                postsDao.insertPosts(listPost)
            }

            override fun fetchFromLocal(): Flow<List<Post>> = postsDao.getAllPosts()

            override suspend fun fetchFromRemote(): Response<TweetNetworkResponse> =
                twitterDemoService.getPosts()

            override suspend fun deleteDbData() {
                postsDao.deleteAllPosts()
            }
        }.asFlow()
    }

    /**
     * Get post by search val
     * searchText - Search text
     * returns List of Posts
     */
    fun getPostBySearchVal(searchText: String): Flow<List<Post>> =
        postsDao.getPostBySearchVal(searchText)

}
