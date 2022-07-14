package com.example.newsappjetpackcompose.presentation.view.compose.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.newsappjetpackcompose.R

val CairoMedium = FontFamily(
    Font(R.font.cairo_medium)
)

val CairoRegular = FontFamily(
    Font(R.font.cairo_regular)
)

val CairoVariableFont = FontFamily(
    Font(R.font.cairo_variablefont_wght)
)

val ShortStack = FontFamily(
    Font(R.font.shortstack_regular)
)

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    h1 = TextStyle(
        fontFamily = CairoMedium
    ),
    h2 = TextStyle(
        fontFamily = CairoRegular
    ),
    h3 = TextStyle(
        fontFamily = CairoVariableFont
    ),
    h4 = TextStyle(
        fontFamily = ShortStack
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)