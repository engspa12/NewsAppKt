package com.example.newsappjetpackcompose.data.network.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Fields(
    @SerializedName("headline")
    @Expose
    val headline: String?,
    @SerializedName("byline")
    @Expose
    val byline: String?,
    @SerializedName("thumbnail")
    @Expose
    val thumbnail: String?
)