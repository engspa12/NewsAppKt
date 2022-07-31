package com.example.newsappjetpackcompose.presentation.navigation

sealed class Screen(val route: String) {
    object WelcomeScreen: Screen("welcome_screen")
    object ArticlesScreen: Screen("articles_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}