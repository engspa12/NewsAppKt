package com.example.newsappjetpackcompose.domain.service

import com.example.newsappjetpackcompose.domain.util.NewsInputValidationError
import com.example.newsappjetpackcompose.domain.util.NewsResponseError
import com.example.newsappjetpackcompose.presentation.model.ArticleView
import com.example.newsappjetpackcompose.util.ResultWrapper
import io.reactivex.Observable

interface NewsService {
    fun getNews(searchTerm: String, searchType: String): Observable<ResultWrapper<List<ArticleView>, NewsResponseError>>
    fun checkInputSearch(inputString: String): ResultWrapper<Unit, NewsInputValidationError>
}