package com.example.newsappjetpackcompose.data.network.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Fields(
    @Json(name = "headline")
    val headline: String?,
    @Json(name = "byline")
    val byline: String?,
    @Json(name = "thumbnail")
    val thumbnail: String?
)