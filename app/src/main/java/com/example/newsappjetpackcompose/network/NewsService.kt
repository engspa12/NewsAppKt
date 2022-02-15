package com.example.newsappjetpackcompose.network

import com.example.newsappjetpackcompose.network.theguardian.NewsSearch
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface NewsService {

    @GET("search")
    fun getNews(@QueryMap query: Map<String, String>): Observable<NewsSearch>
}