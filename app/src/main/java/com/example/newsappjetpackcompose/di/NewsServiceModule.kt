package com.example.newsappjetpackcompose.di

import com.example.newsappjetpackcompose.domain.service.NewsService
import com.example.newsappjetpackcompose.domain.service.NewsServiceImpl
import com.example.newsappjetpackcompose.domain.interactor.NewsInteractor
import com.example.newsappjetpackcompose.util.ConnectionChecker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NewsServiceModule {

    @Provides
    fun provideService(connectionChecker: ConnectionChecker, newsInteractor: NewsInteractor): NewsService {
        return NewsServiceImpl(connectionChecker, newsInteractor)
    }
}