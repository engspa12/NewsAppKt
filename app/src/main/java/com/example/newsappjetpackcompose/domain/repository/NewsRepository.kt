package com.example.newsappjetpackcompose.domain.repository

import com.example.newsappjetpackcompose.domain.model.ArticleDomain
import io.reactivex.Observable

interface NewsRepository {
    fun getNewsData(searchTerm: String, searchType: String): Observable<List<ArticleDomain>>
}