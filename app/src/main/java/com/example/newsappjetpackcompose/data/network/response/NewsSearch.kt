package com.example.newsappjetpackcompose.data.network.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewsSearch(
    @Json(name = "response")
    val response: Response
)