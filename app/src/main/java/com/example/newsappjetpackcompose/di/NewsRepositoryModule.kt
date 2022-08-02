package com.example.newsappjetpackcompose.di

import com.example.newsappjetpackcompose.data.network.datasource.NewsNetworkDataSource
import com.example.newsappjetpackcompose.data.network.model.ArticleNetwork
import com.example.newsappjetpackcompose.data.repository.NewsRepositoryImpl
import com.example.newsappjetpackcompose.domain.model.ArticleDomain
import com.example.newsappjetpackcompose.domain.repository.NewsRepository
import com.example.newsappjetpackcompose.domain.util.NetworkMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NewsRepositoryModule {

    @Provides
    fun provideRepository(newsNetworkDataSource: NewsNetworkDataSource,
                          networkMapper: NetworkMapper<ArticleNetwork, ArticleDomain>
    ): NewsRepository {
        return NewsRepositoryImpl(newsNetworkDataSource, networkMapper)
    }
}