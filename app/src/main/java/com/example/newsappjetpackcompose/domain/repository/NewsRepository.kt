package com.example.newsappjetpackcompose.domain.repository

import com.example.newsappjetpackcompose.data.network.theguardian.Result
import io.reactivex.Observable

interface NewsRepository {

    fun getNewsData(searchTerm: String, sortType: String): Observable<Result>
    fun getNewsDataFromNetwork(queryParams: Map<String, String>): Observable<Result>
    fun getNewsDataFromCache(): Observable<Result>
}