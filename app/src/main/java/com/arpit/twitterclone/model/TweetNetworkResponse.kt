package com.arpit.twitterclone.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TweetNetworkResponse(
    @Json(name = "success")
    var success: Boolean = false,
    @Json(name = "data")
    var data: List<PostItem>
)

data class PostItem(
    @Json(name = "name")
    var name: String? = null,
    @Json(name = "handle")
    var handle: String? = null,
    @Json(name = "favoriteCount")
    var favoriteCount: Int = 0,
    @Json(name = "retweetCount")
    var retweetCount: Int = 0,
    @Json(name = "profileImageUrl")
    var profileImageUrl: String? = null,
    @Json(name = "text")
    var text: String? = null
)