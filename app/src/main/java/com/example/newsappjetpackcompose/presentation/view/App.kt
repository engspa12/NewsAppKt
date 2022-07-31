package com.example.newsappjetpackcompose.presentation.view

import android.content.Context
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.navigation.compose.rememberNavController
import com.example.newsappjetpackcompose.presentation.navigation.Navigation

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