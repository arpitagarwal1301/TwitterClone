package com.arpit.twitterclone.data.repository

import com.arpit.twitterclone.data.local.dao.PostsDao
import com.arpit.twitterclone.data.remoteapi.TwitterCloneService
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Singleton repository for fetching data from remote and storing it in database
 * for offline capability. This is Single source of data.
 */
@Singleton
class PostsRepository @Inject constructor(
    private val postsDao: PostsDao,
    private val twitterDemoService: TwitterCloneService
) {

}
