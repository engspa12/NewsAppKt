package com.example.newsappjetpackcompose.presentation.state

import com.example.newsappjetpackcompose.util.StringWrapper

sealed class WelcomeUIState(
    val errorMessage: StringWrapper = StringWrapper.SimpleString(""),
) {
    object Success: WelcomeUIState()
    class Error(errorMessage: StringWrapper): WelcomeUIState(errorMessage = errorMessage)
}