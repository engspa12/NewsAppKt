package com.example.newsappjetpackcompose.util

import android.content.Context

interface Helper {

    fun isOnline(): Boolean
    fun getNoInternetMessage(): String
}