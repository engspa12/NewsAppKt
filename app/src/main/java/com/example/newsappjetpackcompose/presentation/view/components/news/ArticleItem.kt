package com.example.newsappjetpackcompose.presentation.view.components.news

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.newsappjetpackcompose.R
import com.example.newsappjetpackcompose.presentation.model.ArticleView
import com.example.newsappjetpackcompose.presentation.view.theme.fontSize
import com.example.newsappjetpackcompose.presentation.view.theme.spacing
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ArticleItem(
    articleView: ArticleView,
    modifier: Modifier = Modifier,
    onItemClicked: () -> Unit
){
    Row(
        modifier = modifier
            .clickable {
                onItemClicked()
            },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        GlideImage(
            imageModel = articleView.thumbnailUrl,
            requestOptions = {
                RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            },
            shimmerParams = ShimmerParams(
                baseColor = MaterialTheme.colors.background,
                highlightColor = Color.Green,
                durationMillis = 350,
                dropOff = 0.65f,
                tilt = 20f
            ),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxHeight()
                .requiredWidth(width = MaterialTheme.spacing.dimen120)
                .background(color = MaterialTheme.colors.onSurface)
                .padding(MaterialTheme.spacing.dimen4),
            error = ImageBitmap.imageResource(R.drawable.no_image_available)
        )
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .background(color = MaterialTheme.colors.surface)
                .border(width = MaterialTheme.spacing.dimen4, color = MaterialTheme.colors.onSurface)
                .padding(MaterialTheme.spacing.dimen8)
        ) {
            Text(
                text = articleView.title,
                fontSize = MaterialTheme.fontSize.size14,
                style = MaterialTheme.typography.h4)
            Spacer(
                modifier = Modifier
                    .weight(1f)
            )
            Text(
                text = articleView.author,
                fontSize = MaterialTheme.fontSize.size12,
                style = MaterialTheme.typography.h3
            )
            Row(modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = articleView.releaseDate,
                    fontSize = MaterialTheme.fontSize.size10,
                    style = MaterialTheme.typography.h1
                )
                Text(
                    text = articleView.sectionName,
                    fontSize = MaterialTheme.fontSize.size10,
                    style = MaterialTheme.typography.h4
                )
            }
        }
    }
}