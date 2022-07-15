package com.example.newsappjetpackcompose.di

import com.example.newsappjetpackcompose.data.network.datasource.NewsService
import com.example.newsappjetpackcompose.data.network.model.ArticleNetwork
import com.example.newsappjetpackcompose.data.repository.NewsRepositoryImpl
import com.example.newsappjetpackcompose.data.helper.NetworkMapper
import com.example.newsappjetpackcompose.domain.model.ArticleDomain
import com.example.newsappjetpackcompose.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NewsRepositoryModule {

    @Provides
    fun provideRepository(newsService: NewsService,
                          networkMapper: NetworkMapper<ArticleNetwork, ArticleDomain>): NewsRepository {
        return NewsRepositoryImpl(newsService, networkMapper)
    }
}