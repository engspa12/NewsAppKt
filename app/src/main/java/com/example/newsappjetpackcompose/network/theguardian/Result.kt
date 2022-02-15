package com.example.newsappjetpackcompose.network.theguardian

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("id")
    @Expose
    var id: String,

    @SerializedName("type")
    @Expose
    val type: String,

    @SerializedName("sectionId")
    @Expose
    val sectionId: String,

    @SerializedName("sectionName")
    @Expose
    val sectionName: String,

    @SerializedName("webPublicationDate")
    @Expose
    val webPublicationDate: String,

    @SerializedName("webTitle")
    @Expose
    val webTitle: String,

    @SerializedName("webUrl")
    @Expose
    val webUrl: String,

    @SerializedName("apiUrl")
    @Expose
    val apiUrl: String,

    @SerializedName("fields")
    @Expose
    val fields: Fields,

    @SerializedName("isHosted")
    @Expose
    val isHosted: Boolean,

    @SerializedName("pillarId")
    @Expose
    val pillarId: String,

    @SerializedName("pillarName")
    @Expose
    val pillarName: String
)