package com.example.newsappjetpackcompose.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.newsappjetpackcompose.domain.service.NewsService
import com.example.newsappjetpackcompose.domain.util.NewsInputValidationError
import com.example.newsappjetpackcompose.presentation.state.InputValidationResult
import com.example.newsappjetpackcompose.util.ResultWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val newsService: NewsService
) : ViewModel() {

    fun validateInput(inputString: String): InputValidationResult  {
        return when(val validation = newsService.checkInputSearch(inputString)){
            is ResultWrapper.Success -> {
                InputValidationResult.Success
            }
            is ResultWrapper.Failure -> {
                processError(validation.error)
            }
        }
    }

    private fun processError(error: NewsInputValidationError): InputValidationResult{
        return when(error){
            NewsInputValidationError.NOT_ENOUGH_CHARACTERS -> InputValidationResult.NotEnoughCharactersError
            NewsInputValidationError.EMPTY_INPUT -> InputValidationResult.NotEnoughCharactersError
        }
    }
}