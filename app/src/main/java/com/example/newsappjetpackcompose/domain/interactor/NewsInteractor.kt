package com.example.newsappjetpackcompose.domain.interactor

import com.example.newsappjetpackcompose.presentation.model.ArticleView
import io.reactivex.Observable

interface NewsInteractor {
    fun sendData(searchTerm: String, sortType: String): Observable<List<ArticleView>>
}