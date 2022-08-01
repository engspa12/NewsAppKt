package com.example.newsappjetpackcompose.di

import com.example.newsappjetpackcompose.data.network.datasource.NewsNetworkDataSource
import com.example.newsappjetpackcompose.global.Constants
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NewsRetrofitModule {

    companion object {
        private const val BASE_URL =  Constants.BASE_URL
    }

    @Singleton
    @Provides
    fun provideMoshi() = Moshi.Builder().build()

    @Singleton
    @Provides
    fun provideRetrofit(baseUrl: String, mosh: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(mosh))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideNewsService(mosh: Moshi): NewsNetworkDataSource {
        return provideRetrofit(BASE_URL, mosh).create(NewsNetworkDataSource::class.java)
    }

}