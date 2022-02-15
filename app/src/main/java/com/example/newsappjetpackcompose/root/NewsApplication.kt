package com.example.newsappjetpackcompose.root

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex

class NewsApplication: Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}