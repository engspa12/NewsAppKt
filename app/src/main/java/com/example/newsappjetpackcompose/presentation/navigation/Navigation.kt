package com.example.newsappjetpackcompose.presentation.navigation

import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.example.newsappjetpackcompose.R
import com.example.newsappjetpackcompose.global.Constants
import com.example.newsappjetpackcompose.presentation.view.compose.components.TopBar
import com.example.newsappjetpackcompose.presentation.view.compose.screens.ArticlesScreen
import com.example.newsappjetpackcompose.presentation.view.compose.screens.WelcomeScreen
import com.example.newsappjetpackcompose.presentation.viewmodel.NewsViewModel

@Composable
fun Navigation(
    context: Context,
    scaffoldState: ScaffoldState,
    navController: NavHostController
) {

    var searchType by rememberSaveable { mutableStateOf("") }
    var searchInput by rememberSaveable { mutableStateOf("") }
    var showMenu by remember { mutableStateOf(false) }
    var navigationType by rememberSaveable { mutableStateOf(Constants.NavType.NAV_MAIN)}

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBar(
                showMenu = showMenu,
                titleTopBar = stringResource(id = R.string.app_name),
                navigationType = navigationType,
                navController = navController,
                onDismissMenu = {
                    showMenu = false
                },
                onMenuIconClick = {
                    showMenu = !showMenu
                }
            ) { newSearchType ->
                showMenu = false
                searchType = newSearchType
            }
        }
    ) { paddingValues ->
        NavHost(navController = navController, startDestination = "news", modifier = Modifier.padding(paddingValues)){
            navigation(startDestination = Screen.MainScreen.route, route = "news") {
                composable(
                    route = Screen.MainScreen.route
                ) {
                    navigationType = Constants.NavType.NAV_MAIN
                    WelcomeScreen(
                        searchType = searchType,
                        searchInput = searchInput,
                        onSearchInputChanged = {
                            searchInput = it
                        },
                        onSearchButtonClicked = {
                            navController.navigate(Screen.DetailScreen.withArgs(searchInput, searchType))
                        }
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
                    navigationType = Constants.NavType.NAV_DETAILS
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

}