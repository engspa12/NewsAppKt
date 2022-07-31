package com.example.newsappjetpackcompose.presentation.view.components.news

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.newsappjetpackcompose.R
import com.example.newsappjetpackcompose.presentation.model.ArticleView
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
                .requiredWidth(width = 120.dp)
                .background(color = MaterialTheme.colors.onSurface)
                .padding(4.dp),
            error = ImageBitmap.imageResource(R.drawable.no_image_available)
        )
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .background(color = MaterialTheme.colors.surface)
                .border(width = 4.dp, color = MaterialTheme.colors.onSurface)
                .padding(8.dp)
        ) {
            Text(
                text = articleView.title,
                fontSize = 14.sp,
                style = MaterialTheme.typography.h4)
            Spacer(
                modifier = Modifier
                    .weight(1f)
            )
            Text(
                text = articleView.author,
                fontSize = 12.sp,
                style = MaterialTheme.typography.h3
            )
            Row(modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = articleView.releaseDate,
                    fontSize = 10.sp,
                    style = MaterialTheme.typography.h1
                )
                Text(
                    text = articleView.sectionName,
                    fontSize = 10.sp,
                    style = MaterialTheme.typography.h4
                )
            }
        }
    }
}