package com.example.newsappjetpackcompose.di

import com.example.newsappjetpackcompose.domain.interactor.NewsInteractor
import com.example.newsappjetpackcompose.domain.interactor.NewsInteractorImpl
import com.example.newsappjetpackcompose.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NewsInteractorModule {

    @Provides
    fun provideInteractor(newsRepository: NewsRepository): NewsInteractor {
        return NewsInteractorImpl(newsRepository)
    }
}