package com.example.newsappjetpackcompose.util

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

sealed class UIText {
    data class DynamicString(val value: String): UIText()
    class ResourceString(@StringRes val id:Int, val args: Array<Any> = emptyArray()): UIText()

    @Composable
    fun asString(): String {
        return when(this) {
            is DynamicString -> value
            is ResourceString -> stringResource(id = id, formatArgs =  args)
        }
    }

    fun getStringIdResource(): Int? {
        return when(this) {
            is DynamicString -> null
            is ResourceString -> id
        }
    }
}