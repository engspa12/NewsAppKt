package com.example.newsappjetpackcompose.di

import com.example.newsappjetpackcompose.data.network.mapper.ArticleNetworkMapper
import com.example.newsappjetpackcompose.data.network.model.ArticleNetwork
import com.example.newsappjetpackcompose.data.util.NetworkMapper
import com.example.newsappjetpackcompose.domain.model.ArticleDomain
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NewsMapperModule {

    @Provides
    fun provideMapper(): NetworkMapper<ArticleNetwork, ArticleDomain> {
        return ArticleNetworkMapper()
    }
}