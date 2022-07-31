package com.example.newsappjetpackcompose.presentation.navigation

import android.content.Context
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.example.newsappjetpackcompose.global.Constants
import com.example.newsappjetpackcompose.presentation.view.compose.screens.ArticlesScreen
import com.example.newsappjetpackcompose.presentation.view.compose.screens.WelcomeScreen
import com.example.newsappjetpackcompose.presentation.viewmodel.NewsViewModel

@Composable
fun Navigation(
    context: Context,
    navController: NavHostController,
    searchType: String,
    onNavigationChange: (Constants.NavType) -> Unit,
    modifier: Modifier = Modifier
) {

    var searchInput by rememberSaveable { mutableStateOf("") }

    NavHost(navController = navController, startDestination = "news"){
        navigation(startDestination = Screen.MainScreen.route + "/searchType", route = "news") {
            composable(
                route = Screen.MainScreen.route + "/searchType",
                arguments = listOf(
                    navArgument("searchType") {
                        type = NavType.StringType
                        defaultValue = searchType
                        nullable = false
                    }
                )
            ) {
                onNavigationChange(Constants.NavType.NAV_MAIN)
                WelcomeScreen(
                    searchType = searchType,
                    searchInput = searchInput,
                    onSearchInputChanged = {
                        searchInput = it
                    },
                    onSearchButtonClicked = {
                        navController.navigate(Screen.DetailScreen.withArgs(searchInput, searchType))
                    },
                    modifier = modifier
                )
            }
            composable(
                route = Screen.DetailScreen.route + "/{searchInput}/{searchType}",
                arguments = listOf(
                    navArgument("searchInput") {
                        type = NavType.StringType
                        defaultValue = ""
                        nullable = false
                    },
                    navArgument("searchType") {
                        type = NavType.StringType
                        defaultValue = ""
                        nullable = false
                    }
                )
            ) { backStackEntry ->
                onNavigationChange(Constants.NavType.NAV_DETAILS)
                val newsViewModel = hiltViewModel<NewsViewModel>()
                ArticlesScreen(
                    context = context,
                    viewModel = newsViewModel,
                    searchInput = backStackEntry.arguments?.getString("searchInput") ?: "",
                    searchType = backStackEntry.arguments?.getString("searchType") ?: ""
                )
            }
        }
    }
}