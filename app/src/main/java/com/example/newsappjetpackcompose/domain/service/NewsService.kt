package com.example.newsappjetpackcompose.domain.service

import com.example.newsappjetpackcompose.presentation.model.ArticleView
import com.example.newsappjetpackcompose.util.ResultWrapper
import com.example.newsappjetpackcompose.util.UIText
import io.reactivex.Observable

interface NewsService {
    fun getNews(searchTerm: String, searchType: String): Observable<ResultWrapper<List<ArticleView>>>
    fun checkInputSearch(inputString: String): ResultWrapper<UIText>
}