package com.example.newsappjetpackcompose.domain.interactor

import com.example.newsappjetpackcompose.data.network.theguardian.Result
import com.example.newsappjetpackcompose.domain.repository.NewsRepository
import io.reactivex.Observable
import javax.inject.Inject

class NewsInteractorImpl @Inject constructor(
    private val newsRepository: NewsRepository
): NewsInteractor {

    override fun sendData(searchTerm: String, sortType: String): Observable<Result> {
        return newsRepository.getNewsData(searchTerm, sortType)
    }
}