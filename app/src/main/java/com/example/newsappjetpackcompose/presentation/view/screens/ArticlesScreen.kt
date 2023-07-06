package com.example.newsappjetpackcompose.presentation.view.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.newsappjetpackcompose.R
import com.example.newsappjetpackcompose.presentation.state.ArticlesUIState
import com.example.newsappjetpackcompose.presentation.util.mapToStringResource
import com.example.newsappjetpackcompose.presentation.view.components.news.ArticlesList
import com.example.newsappjetpackcompose.presentation.view.components.news.ErrorIndicator
import com.example.newsappjetpackcompose.presentation.view.components.news.ProgressBar
import com.example.newsappjetpackcompose.presentation.viewmodel.NewsViewModel

@Composable
fun ArticlesScreen(
    context: Context,
    viewModel: NewsViewModel,
    searchInput: String,
    searchType: String
){

    val lazyState = rememberLazyListState()
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.getNews(searchInput, searchType)
    }

    when(val state = uiState) {
        is ArticlesUIState.Success -> {
            ArticlesList(
                lazyState = lazyState,
                list = state.value
            ) { articleURL ->
               val intent = Intent(Intent.ACTION_VIEW, Uri.parse(articleURL))
                if (intent.resolveActivity(context.packageManager) != null) {
                    context.startActivity(intent)
                }
            }
        }
        is ArticlesUIState.Loading -> {
            ProgressBar(
                message = stringResource(id = R.string.loading_news_message),
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight(Alignment.CenterVertically)
            )
        }
        is ArticlesUIState.GenericError, ArticlesUIState.NoConnectionError, ArticlesUIState.NoArticlesFoundError  -> {
            ErrorIndicator(
                errorMessage = stringResource(id = state.mapToStringResource()),
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight(Alignment.CenterVertically)
                    .padding(horizontal = 20.dp)
            )
        }
    }

}