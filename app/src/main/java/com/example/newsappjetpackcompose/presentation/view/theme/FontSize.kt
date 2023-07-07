package com.example.newsappjetpackcompose.presentation.view.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

data class FontSize(
    val size8: TextUnit = 8.sp,
    val size10: TextUnit = 10.sp,
    val size12: TextUnit = 12.sp,
    val size14: TextUnit = 14.sp,
    val size16: TextUnit = 16.sp,
    val size20: TextUnit = 20.sp,
    val size22: TextUnit = 22.sp,
    val size28: TextUnit = 28.sp,
    val size30: TextUnit = 30.sp
)

val LocalFontSize = compositionLocalOf { FontSize() }

val MaterialTheme.fontSize: FontSize
    @Composable
    @ReadOnlyComposable
    get() = LocalFontSize.current