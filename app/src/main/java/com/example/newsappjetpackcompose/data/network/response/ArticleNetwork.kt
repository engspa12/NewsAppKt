package com.example.newsappjetpackcompose.data.network.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArticleNetwork(
    @Json(name = "id")
    var id: String?,

    @Json(name = "type")
    val type: String?,

    @Json(name = "sectionId")
    val sectionId: String?,

    @Json(name = "sectionName")
    val sectionName: String?,

    @Json(name = "webPublicationDate")
    val webPublicationDate: String?,

    @Json(name = "webTitle")
    val webTitle: String?,

    @Json(name = "webUrl")
    val webUrl: String?,

    @Json(name = "apiUrl")
    val apiUrl: String?,

    @Json(name = "fields")
    val fields: Fields?,

    @Json(name = "isHosted")
    val isHosted: Boolean?,

    @Json(name = "pillarId")
    val pillarId: String?,

    @Json(name = "pillarName")
    val pillarName: String?
)