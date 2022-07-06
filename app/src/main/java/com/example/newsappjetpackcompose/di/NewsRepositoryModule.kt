package com.example.newsappjetpackcompose.di

import com.example.newsappjetpackcompose.data.network.NewsService
import com.example.newsappjetpackcompose.data.network.response.Result
import com.example.newsappjetpackcompose.data.repository.NewsRepositoryImpl
import com.example.newsappjetpackcompose.domain.helper.NetworkMapper
import com.example.newsappjetpackcompose.domain.interactor.NewsInteractor
import com.example.newsappjetpackcompose.domain.interactor.NewsInteractorImpl
import com.example.newsappjetpackcompose.domain.model.ArticleDomain
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
object NewsRepositoryModule {

    @Provides
    fun provideRepository(newsService: NewsService,
                          networkMapper: NetworkMapper<Result, ArticleDomain>): NewsRepository {
        return NewsRepositoryImpl(newsService, networkMapper)
    }
}