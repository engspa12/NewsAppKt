package com.example.newsappjetpackcompose.presentation.view.compose.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsappjetpackcompose.presentation.model.ArticleView
import com.example.newsappjetpackcompose.presentation.view.compose.theme.NewsAppKtTheme

@Composable
fun ArticlesList(
    lazyState: LazyListState,
    list: List<ArticleView>,
    onItemClicked: (String) -> Unit
){
    LazyColumn(
        state = lazyState
    ) {
        itemsIndexed(list) { index, articleView ->
            ArticleItem(
                articleView = articleView,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
            ) {
                onItemClicked(articleView.webUrl)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NewsAppKtTheme {
        val list = ArrayList<ArticleView>()

        list.add(ArticleView("title 1", "section 1", "author 1", "release 1","weburl 1", "thumb 1"))
        list.add(ArticleView("title 2", "section 2", "author 2", "release 2","weburl 2", "thumb 2"))

        ArticlesList(
            lazyState = rememberLazyListState(),
            list = list
        ) {

        }
    }
}