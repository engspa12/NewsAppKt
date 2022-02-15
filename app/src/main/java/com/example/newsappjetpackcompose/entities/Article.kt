package com.example.newsappjetpackcompose.entities

data class Article(
    val title: String,
    val sectionName: String,
    val author: String,
    val releaseDate: String,
    val webUrl: String,
    val thumbnailUrl: String
)