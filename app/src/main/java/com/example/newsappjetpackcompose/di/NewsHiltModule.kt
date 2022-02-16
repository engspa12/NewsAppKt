package com.example.newsappjetpackcompose.di

import com.example.newsappjetpackcompose.data.network.NewsService
import com.example.newsappjetpackcompose.data.repository.NewsRepositoryImpl
import com.example.newsappjetpackcompose.domain.interactor.NewsInteractor
import com.example.newsappjetpackcompose.domain.interactor.NewsInteractorImpl
import com.example.newsappjetpackcompose.domain.repository.NewsRepository
import com.example.newsappjetpackcompose.util.Helper
import com.example.newsappjetpackcompose.util.HelperImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NewsHiltModule {

    @Provides
    fun provideInteractor(newsRepository: NewsRepository): NewsInteractor {
        return NewsInteractorImpl(newsRepository)
    }

    @Provides
    fun provideRepository(newsService: NewsService): NewsRepository {
        return NewsRepositoryImpl(newsService)
    }

    @Provides
    fun provideHelper(): Helper {
        return HelperImpl()
    }
}