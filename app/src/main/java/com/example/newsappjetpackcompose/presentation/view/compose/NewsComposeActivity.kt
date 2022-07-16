package com.example.newsappjetpackcompose.presentation.view.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.newsappjetpackcompose.presentation.view.compose.screens.ArticlesScreen
import com.example.newsappjetpackcompose.presentation.view.compose.theme.NewsAppKtTheme
import com.example.newsappjetpackcompose.presentation.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppKtTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val searchTerm = intent.getStringExtra("search") ?: ""
                    val sortType = intent.getStringExtra("sort_type") ?: ""

                    val viewModel: NewsViewModel = viewModel()
                    val context = LocalContext.current

                    ArticlesScreen(
                        context = context,
                        viewModel = viewModel,
                        searchTerm = searchTerm,
                        sortType = sortType
                    )
                }
            }
        }
    }
}