package com.example.newsappjetpackcompose.util

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

sealed class StringWrapper {
    data class SimpleStringWrapper(val value: String): StringWrapper()
    class ResourceStringWrapper(@StringRes val id:Int, val args: Array<Any> = emptyArray()): StringWrapper()

    @Composable
    fun asString(): String {
        return when(this) {
            is SimpleStringWrapper -> value
            is ResourceStringWrapper -> stringResource(id = id, formatArgs =  args)
        }
    }

    fun getStringIdResource(): Int? {
        return when(this) {
            is SimpleStringWrapper -> null
            is ResourceStringWrapper -> id
        }
    }
}