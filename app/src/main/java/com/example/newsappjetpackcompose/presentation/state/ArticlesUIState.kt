package com.example.newsappjetpackcompose.presentation.state

import com.example.newsappjetpackcompose.presentation.model.ArticleView

sealed class ArticlesUIState(
    val value: List<ArticleView>? = null,
    val errorMessage: String = "",
    val loadingMessage: String = ""
) {
    class Success(value: List<ArticleView>): ArticlesUIState(value)
    class Error(errorMessage: String): ArticlesUIState(errorMessage = errorMessage)
    class Loading(loadingMessage: String) : ArticlesUIState(loadingMessage = loadingMessage)
}