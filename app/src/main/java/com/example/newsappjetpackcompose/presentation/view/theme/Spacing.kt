package com.example.newsappjetpackcompose.presentation.view.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Spacing(
    val dimen0: Dp = 0.dp,
    val dimen4: Dp = 4.dp,
    val dimen8: Dp = 8.dp,
    val dimen12: Dp = 12.dp,
    val dimen16: Dp = 16.dp,
    val dimen18: Dp = 18.dp,
    val dimen20: Dp = 20.dp,
    val dimen28: Dp = 28.dp,
    val dimen30: Dp = 30.dp,
    val dimen40: Dp = 40.dp,
    val dimen50: Dp = 50.dp,
    val dimen120: Dp = 120.dp,
    val dimen140: Dp = 140.dp,
    val dimen150: Dp = 150.dp,
    val dimen250: Dp = 250.dp
)

val LocalSpacing = compositionLocalOf { Spacing() }

val MaterialTheme.spacing: Spacing
    @Composable
    @ReadOnlyComposable
    get() = LocalSpacing.current