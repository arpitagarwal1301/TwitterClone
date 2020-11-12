package com.arpit.twitterclone.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TweetNetworkResponse(
    @Json(name = "success")
    var success: Boolean = false,
    @Json(name = "data")
    var data: List<Post>
)