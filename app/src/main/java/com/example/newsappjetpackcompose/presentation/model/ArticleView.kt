package com.example.newsappjetpackcompose.presentation.model

data class ArticleView(
    val title: String,
    val sectionName: String,
    val author: String,
    val releaseDate: String,
    val webUrl: String,
    val thumbnailUrl: String
)