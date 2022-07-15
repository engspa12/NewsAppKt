package com.example.newsappjetpackcompose.data.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewsSearch(
    @Json(name = "response")
    val response: Response
)