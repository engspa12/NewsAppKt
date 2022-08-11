package com.example.newsappjetpackcompose.di

import android.content.Context
import com.example.newsappjetpackcompose.util.ConnectionChecker
import com.example.newsappjetpackcompose.util.ConnectionCheckerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NewsCheckerModule {

    @Provides
    fun provideConnectionChecker(@ApplicationContext appContext: Context): ConnectionChecker {
        return ConnectionCheckerImpl(appContext)
    }
}