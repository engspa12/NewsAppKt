package com.example.newsappjetpackcompose.data.network.datasource

import com.example.newsappjetpackcompose.data.network.model.NewsSearch
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface NewsNetworkDataSource {

    @GET("search")
    fun getNews(@QueryMap query: Map<String, String>): Observable<NewsSearch>
}