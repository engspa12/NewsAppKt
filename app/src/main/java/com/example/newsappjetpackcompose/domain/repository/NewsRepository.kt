package com.example.newsappjetpackcompose.domain.repository

import com.example.newsappjetpackcompose.data.network.response.Result
import com.example.newsappjetpackcompose.domain.model.ArticleDomain
import io.reactivex.Observable

interface NewsRepository {
    fun getNewsData(searchTerm: String, sortType: String): Observable<List<ArticleDomain>>
}