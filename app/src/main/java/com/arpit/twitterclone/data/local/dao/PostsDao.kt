package com.arpit.twitterclone.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.arpit.twitterclone.model.Post
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO)
 */
@Dao
interface PostsDao {

    /**
     * Insert posts
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPosts(posts: List<Post>)

    /**
     * Get all posts
     */
    @Query("SELECT * FROM ${Post.TABLE_NAME}")
    fun getAllPosts(): Flow<List<Post>>

    /**
     * Search Query
     */
    @Query("SELECT * FROM ${Post.TABLE_NAME} WHERE NAME LIKE :searchText OR HANDLE LIKE :searchText OR TEXT LIKE :searchText")
    fun getPostBySearchVal(searchText: String): Flow<List<Post>>
}
