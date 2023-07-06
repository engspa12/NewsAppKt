package com.example.newsappjetpackcompose.presentation.view.screens

import android.content.Context
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.newsappjetpackcompose.R
import com.example.newsappjetpackcompose.presentation.state.InputValidationResult
import com.example.newsappjetpackcompose.presentation.util.mapToStringResource
import com.example.newsappjetpackcompose.presentation.view.components.welcome.WelcomeContent
import com.example.newsappjetpackcompose.presentation.view.components.welcome.WelcomeContentLand
import com.example.newsappjetpackcompose.presentation.viewmodel.WelcomeViewModel

@Composable
fun WelcomeScreen(
    context: Context,
    viewModel: WelcomeViewModel,
    searchType: String,
    searchInput: String,
    onSearchInputChanged: (String) -> Unit,
    onSearchButtonClicked: (Boolean, String) -> Unit
){
    val configuration = LocalConfiguration.current
    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            WelcomeContentLand(
                searchType = searchType,
                searchInput = searchInput,
                onSearchInputChanged = {
                    onSearchInputChanged(it)
                },
                onSearchButtonClicked = {
                    when(val validation = viewModel.validateInput(searchInput)){
                        is InputValidationResult.Success -> {
                            onSearchButtonClicked(false, "")
                        }
                        is InputValidationResult.EmptyInputError, InputValidationResult.NotEnoughCharactersError -> {
                            onSearchButtonClicked(
                                true,
                                context.getString(validation.mapToStringResource())
                            )
                        }
                    }

                },
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(id = R.color.welcome_background))
                    .padding(horizontal = 20.dp)
            )
        }
        else -> {
            WelcomeContent(
                searchType = searchType,
                searchInput = searchInput,
                onSearchInputChanged = {
                    onSearchInputChanged(it)
                },
                onSearchButtonClicked = {
                    when(val validation = viewModel.validateInput(searchInput)){
                        is InputValidationResult.Success -> {
                            onSearchButtonClicked(false, "")
                        }
                        is InputValidationResult.EmptyInputError, InputValidationResult.NotEnoughCharactersError -> {
                            onSearchButtonClicked(
                                true,
                                context.getString(validation.mapToStringResource())
                            )
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(id = R.color.welcome_background))
                    .padding(horizontal = 20.dp)
            )
        }
    }

}
