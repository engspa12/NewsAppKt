package com.example.newsappjetpackcompose.presentation.view.compose

import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newsappjetpackcompose.global.Constants
import com.example.newsappjetpackcompose.presentation.view.compose.components.TopBar
import com.example.newsappjetpackcompose.presentation.view.compose.screens.ArticlesScreen
import com.example.newsappjetpackcompose.presentation.view.compose.screens.WelcomeScreen
import com.example.newsappjetpackcompose.presentation.viewmodel.NewsViewModel
import com.example.newsappjetpackcompose.R
import com.example.newsappjetpackcompose.presentation.navigation.Navigation
import com.example.newsappjetpackcompose.presentation.navigation.Screen

@Composable
fun App(
    context: Context
) {

    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()

    var searchType by rememberSaveable { mutableStateOf("") }
    var showMenu by remember { mutableStateOf(false) }
    var navigationType by rememberSaveable { mutableStateOf(Constants.NavType.NAV_MAIN)}
    var searchInput by rememberSaveable { mutableStateOf("") }


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
        /*Navigation(
            context = context,
            navController = navController,
            searchType = searchType,
            onNavigationChange = {
                navigationType = it
            },
            modifier = Modifier
                .padding(paddingValues = paddingValues)
        )*/
        NavHost(navController = navController, startDestination = "news"){
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
                        },
                        modifier = Modifier
                            .padding(paddingValues = paddingValues)
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