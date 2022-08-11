package com.example.newsappjetpackcompose.presentation.state

import com.example.newsappjetpackcompose.presentation.model.ArticleView
import com.example.newsappjetpackcompose.util.StringWrapper

sealed class ArticlesUIState(
    val value: List<ArticleView> = emptyList(),
    val errorMessage: StringWrapper = StringWrapper.SimpleStringWrapper(""),
    val loadingMessage: StringWrapper = StringWrapper.SimpleStringWrapper("")
) {
    class Success(value: List<ArticleView>): ArticlesUIState(value)
    class Error(errorMessage: StringWrapper): ArticlesUIState(errorMessage = errorMessage)
    class Loading(loadingMessage: StringWrapper) : ArticlesUIState(loadingMessage = loadingMessage)
}