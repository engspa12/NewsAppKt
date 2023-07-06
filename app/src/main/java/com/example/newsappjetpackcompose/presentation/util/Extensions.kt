package com.example.newsappjetpackcompose.presentation.util

import com.example.newsappjetpackcompose.R
import com.example.newsappjetpackcompose.presentation.state.ArticlesUIState
import com.example.newsappjetpackcompose.presentation.state.InputValidationResult

fun ArticlesUIState.mapToStringResource(): Int {
    return when(this){
        ArticlesUIState.GenericError -> R.string.error_data_retrieval
        ArticlesUIState.NoConnectionError -> R.string.no_internet_connection
        ArticlesUIState.NoArticlesFoundError -> R.string.no_articles_found_message
        else -> R.string.error_generic
    }
}

fun InputValidationResult.mapToStringResource(): Int {
    return when(this){
        InputValidationResult.NotEnoughCharactersError -> R.string.error_not_enough_characters
        InputValidationResult.EmptyInputError -> R.string.error_empty_input
        else -> R.string.error_generic
    }
}