package com.example.newsappjetpackcompose.di

import android.content.Context
import com.example.newsappjetpackcompose.util.Validator
import com.example.newsappjetpackcompose.util.ValidatorImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NewsValidatorModule {

    @Provides
    fun provideHelper(@ApplicationContext appContext: Context): Validator {
        return ValidatorImpl(appContext)
    }
}