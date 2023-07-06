package com.example.newsappjetpackcompose.di

import android.content.Context
import com.example.newsappjetpackcompose.domain.util.ConnectionChecker
import com.example.newsappjetpackcompose.domain.util.ConnectionCheckerImpl
import com.example.newsappjetpackcompose.domain.util.InputValidator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NewsHelpersModule {

    @Provides
    fun provideConnectionChecker(@ApplicationContext appContext: Context): ConnectionChecker {
        return ConnectionCheckerImpl(appContext)
    }

    @Provides
    fun provideInputValidator(): InputValidator {
        return InputValidator()
    }
}