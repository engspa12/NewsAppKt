package com.example.newsappjetpackcompose.data.network.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Response(
    @Json(name = "status")
    val status: String?,
    @Json(name = "userTier")
    val userTier: String?,
    @Json(name = "total")
    val total: Int?,
    @Json(name = "startIndex")
    val startIndex: Int?,
    @Json(name = "pageSize")
    val pageSize: Int?,
    @Json(name = "currentPage")
    val currentPage: Int?,
    @Json(name = "pages")
    val pages: Int?,
    @Json(name = "orderBy")
    val orderBy: String?,
    @Json(name = "results")
    val articlesNetwork: List<ArticleNetwork>?
)