package com.example.newsappjetpackcompose.network.theguardian

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("status")
    @Expose
    val status: String,
    @SerializedName("userTier")
    @Expose
    val userTier: String,
    @SerializedName("total")
    @Expose
    val total: Int,
    @SerializedName("startIndex")
    @Expose
    val startIndex: Int,
    @SerializedName("pageSize")
    @Expose
    val pageSize: Int,
    @SerializedName("currentPage")
    @Expose
    val currentPage: Int,
    @SerializedName("pages")
    @Expose
    val pages: Int,
    @SerializedName("orderBy")
    @Expose
    val orderBy: String,
    @SerializedName("results")
    @Expose
    val results: List<Result>
)