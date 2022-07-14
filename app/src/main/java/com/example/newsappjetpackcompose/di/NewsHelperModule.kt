package com.example.newsappjetpackcompose.di

import android.content.Context
import com.example.newsappjetpackcompose.util.Helper
import com.example.newsappjetpackcompose.util.HelperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NewsHelperModule {

    @Provides
    fun provideHelper(@ApplicationContext appContext: Context): Helper {
        return HelperImpl(appContext)
    }
}