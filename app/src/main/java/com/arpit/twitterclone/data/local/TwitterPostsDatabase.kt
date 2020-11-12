package com.arpit.twitterclone.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.arpit.twitterclone.data.local.dao.PostsDao
import com.arpit.twitterclone.model.Post

/**
 * Twitter posts database
 */
@Database(
    entities = [Post::class],
    version = 1
)
abstract class TwitterPostsDatabase : RoomDatabase() {

    /**
     * Get posts dao
     */
    abstract fun getPostsDao(): PostsDao

    companion object {
        const val DB_NAME = "twitter_database"

        @Volatile
        private var INSTANCE: TwitterPostsDatabase? = null

        fun getInstance(context: Context): TwitterPostsDatabase {
            val tempInstance =
                INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TwitterPostsDatabase::class.java,
                    DB_NAME
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}
