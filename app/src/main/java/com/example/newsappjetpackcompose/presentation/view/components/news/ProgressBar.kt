package com.example.newsappjetpackcompose.presentation.view.components.news

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.newsappjetpackcompose.presentation.view.theme.spacing

@Composable
fun ProgressBar(
    message: String,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
    ) {
        CircularProgressIndicator(
            color = MaterialTheme.colors.primaryVariant,
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .requiredSize(MaterialTheme.spacing.dimen50)
        )
        Text(
            text = message,
            color = MaterialTheme.colors.primaryVariant,
            modifier =
            Modifier.padding(vertical = MaterialTheme.spacing.dimen16)
                .align(alignment = Alignment.CenterHorizontally)
        )
    }
}