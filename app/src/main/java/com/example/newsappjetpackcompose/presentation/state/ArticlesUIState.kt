package com.example.newsappjetpackcompose.presentation.state

import com.example.newsappjetpackcompose.presentation.model.ArticleView

sealed class ArticlesUIState(
    val data: List<ArticleView>? = null,
    val errorMessage: String = "",
    val loadingMessage: String = ""
) {
    class Success(data: List<ArticleView>): ArticlesUIState(data)
    class Error(errorMessage: String): ArticlesUIState(errorMessage = errorMessage)
    class Loading(loadingMessage: String) : ArticlesUIState(loadingMessage = loadingMessage)
}