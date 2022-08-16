package com.example.newsappjetpackcompose.presentation.view

import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newsappjetpackcompose.R
import com.example.newsappjetpackcompose.global.Constants
import com.example.newsappjetpackcompose.presentation.navigation.Screen
import com.example.newsappjetpackcompose.presentation.view.components.shared.TopBar
import com.example.newsappjetpackcompose.presentation.view.screens.ArticlesScreen
import com.example.newsappjetpackcompose.presentation.view.screens.WelcomeScreen
import com.example.newsappjetpackcompose.presentation.viewmodel.NewsViewModel
import com.example.newsappjetpackcompose.presentation.viewmodel.WelcomeViewModel
import kotlinx.coroutines.launch

@Composable
fun App(
    context: Context
) {

    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    var searchType by rememberSaveable { mutableStateOf(Constants.RELEVANCE_SEARCH_TYPE) }
    var searchInput by rememberSaveable { mutableStateOf("") }
    var showMenu by remember { mutableStateOf(false) }
    var navigationType by rememberSaveable { mutableStateOf(Constants.NavType.NAV_MAIN)}

    Scaffold(
        scaffoldState = scaffoldState,
        snackbarHost = {
            SnackbarHost(hostState = it) { data ->
                Snackbar(
                    backgroundColor = MaterialTheme.colors.background,
                    contentColor = MaterialTheme.colors.onBackground,
                    snackbarData = data
                )
            }
        },
        topBar = {
            TopBar(
                showMenu = showMenu,
                titleTopBar = stringResource(id = R.string.app_name),
                navigationType = navigationType,
                goBackToMain = {
                    navController.popBackStack()
                },
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
            navigation(startDestination = Screen.WelcomeScreen.route, route = "news") {
                composable(
                    route = Screen.WelcomeScreen.route
                ) {
                    navigationType = Constants.NavType.NAV_MAIN
                    val welcomeViewModel = hiltViewModel<WelcomeViewModel>()
                    WelcomeScreen(
                        context = context,
                        viewModel = welcomeViewModel,
                        searchType = searchType,
                        searchInput = searchInput,
                        onSearchInputChanged = {
                            searchInput = it
                        },
                        onSearchButtonClicked = { errorPresent, errorMessage ->
                            if(errorPresent){
                                coroutineScope.launch {
                                    scaffoldState.snackbarHostState.showSnackbar(
                                        errorMessage
                                    )
                                }
                            } else {
                                navController.navigate(Screen.ArticlesScreen.withArgs(searchInput.trim(), searchType))
                            }
                        }
                    )
                }
                composable(
                    route = Screen.ArticlesScreen.route + "/{searchInput}/{searchType}",
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