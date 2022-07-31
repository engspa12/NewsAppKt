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

    Navigation(
        context = context,
        scaffoldState = scaffoldState,
        navController = navController
    )

}