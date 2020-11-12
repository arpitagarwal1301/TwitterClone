package com.arpit.twitterclone.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.arpit.twitterclone.model.Post.Companion.TABLE_NAME

/**
 * Data class for Database entity.
 */
@Entity(tableName = TABLE_NAME)
data class Post(
    var name: String? = null,
    var handle: String? = null,
    @PrimaryKey
    var favoriteCount: Int = 0,
    var retweetCount: Int = 0,
    var profileImageUrl: String? = null,
    var text: String? = null
) {
    companion object {
        const val TABLE_NAME = "twitter_posts"
    }
}
