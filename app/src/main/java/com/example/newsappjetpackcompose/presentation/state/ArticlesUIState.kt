package com.example.newsappjetpackcompose.presentation.state

import com.example.newsappjetpackcompose.presentation.model.ArticleView
import com.example.newsappjetpackcompose.util.UIText

sealed class ArticlesUIState(
    val value: List<ArticleView> = emptyList(),
    val errorMessage: UIText = UIText.DynamicString(""),
    val loadingMessage: UIText = UIText.DynamicString("")
) {
    class Success(value: List<ArticleView>): ArticlesUIState(value)
    class Error(errorMessage: UIText): ArticlesUIState(errorMessage = errorMessage)
    class Loading(loadingMessage: UIText) : ArticlesUIState(loadingMessage = loadingMessage)
}