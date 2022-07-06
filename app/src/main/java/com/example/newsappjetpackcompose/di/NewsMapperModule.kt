package com.example.newsappjetpackcompose.di

import com.example.newsappjetpackcompose.data.network.response.ArticleNetworkMapper
import com.example.newsappjetpackcompose.data.network.response.Result
import com.example.newsappjetpackcompose.domain.helper.NetworkMapper
import com.example.newsappjetpackcompose.domain.model.ArticleDomain
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NewsMapperModule {

    @Provides
    fun provideMapper(): NetworkMapper<Result, ArticleDomain> {
        return ArticleNetworkMapper()
    }
}