package com.example.newsappjetpackcompose.di

import com.example.newsappjetpackcompose.data.network.datasource.NewsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NewsRetrofitModule {

    companion object {
        private const val BASE_URL =  "https://content.guardianapis.com/"
    }

    @Singleton
    @Provides
    fun provideRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideNewsService(): NewsService {
        return provideRetrofit(BASE_URL).create(NewsService::class.java)
    }

}