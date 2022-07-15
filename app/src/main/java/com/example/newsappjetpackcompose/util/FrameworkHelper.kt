package com.example.newsappjetpackcompose.util

import android.content.Context

interface FrameworkHelper {

    fun isOnline(): Boolean
    fun getNoInternetMessage(): String
}