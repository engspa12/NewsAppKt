package com.example.newsappjetpackcompose.data.network.theguardian

import com.google.gson.annotations.SerializedName

data class NewsSearch(
    @SerializedName("response")
    val response: Response
)