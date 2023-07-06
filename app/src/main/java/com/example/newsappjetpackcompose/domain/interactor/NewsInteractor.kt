package com.example.newsappjetpackcompose.domain.interactor

import com.example.newsappjetpackcompose.domain.util.NewsResponseError
import com.example.newsappjetpackcompose.presentation.model.ArticleView
import com.example.newsappjetpackcompose.util.ResultWrapper
import io.reactivex.Observable

interface NewsInteractor {
    fun getNews(searchTerm: String, searchType: String): Observable<ResultWrapper<List<ArticleView>, NewsResponseError>>
}