package com.example.newsappjetpackcompose.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.newsappjetpackcompose.domain.service.NewsService
import com.example.newsappjetpackcompose.presentation.state.WelcomeUIState
import com.example.newsappjetpackcompose.util.ResultWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val newsService: NewsService
) : ViewModel() {

    fun validateInput(inputString: String): WelcomeUIState  {
        return when(val validation = newsService.checkInputSearch(inputString)){
            is ResultWrapper.Success -> {
                WelcomeUIState.Success
            }
            is ResultWrapper.Failure -> {
                WelcomeUIState.Error(validation.errorMessage)
            }
        }
    }
}