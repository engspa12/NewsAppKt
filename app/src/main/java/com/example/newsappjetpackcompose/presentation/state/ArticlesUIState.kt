package com.example.newsappjetpackcompose.presentation.state

import com.example.newsappjetpackcompose.presentation.model.ArticleView

sealed class ArticlesUIState {
    data class Success(val value: List<ArticleView>): ArticlesUIState()
    object GenericError: ArticlesUIState()
    object NoConnectionError: ArticlesUIState()
    object NoArticlesFoundError: ArticlesUIState()
    object Loading : ArticlesUIState()
}