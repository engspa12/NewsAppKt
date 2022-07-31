package com.example.newsappjetpackcompose.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.newsappjetpackcompose.R
import com.example.newsappjetpackcompose.presentation.state.WelcomeUIState
import com.example.newsappjetpackcompose.util.UIText
import com.example.newsappjetpackcompose.util.Validator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val validator: Validator
) : ViewModel() {

    fun validateInput(inputString: String): WelcomeUIState  {
        return if(validator.isEmptyString(inputString)){
            WelcomeUIState.Error(UIText.ResourceString(id = R.string.error_empty_input))
        } else if(validator.isLessThanThreeCharacters(inputString)) {
            WelcomeUIState.Error(UIText.ResourceString(id = R.string.error_not_enough_characters))
        } else {
            WelcomeUIState.Success
        }
    }
}