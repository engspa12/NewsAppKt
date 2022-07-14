package com.example.newsappjetpackcompose.presentation.state

import com.example.newsappjetpackcompose.presentation.model.ArticleView

sealed class ArticlesUIState {
    data class Success(val data: List<ArticleView>): ArticlesUIState()
    data class Error(val errorMessage: String): ArticlesUIState()
    data class Loading(val loadingMessage: String) : ArticlesUIState()
}