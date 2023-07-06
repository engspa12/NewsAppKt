package com.example.newsappjetpackcompose.presentation.state

sealed class InputValidationResult {
    object Success: InputValidationResult()
    object NotEnoughCharactersError: InputValidationResult()
    object EmptyInputError: InputValidationResult()
}