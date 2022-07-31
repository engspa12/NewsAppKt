package com.example.newsappjetpackcompose.presentation.state

import com.example.newsappjetpackcompose.util.UIText

sealed class WelcomeUIState(
    val errorMessage: UIText = UIText.DynamicString(""),
) {
    object Success: WelcomeUIState()
    class Error(errorMessage: UIText): WelcomeUIState(errorMessage = errorMessage)
}