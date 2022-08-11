package com.example.newsappjetpackcompose.domain.service

import com.example.newsappjetpackcompose.R
import com.example.newsappjetpackcompose.domain.interactor.NewsInteractor
import com.example.newsappjetpackcompose.presentation.model.ArticleView
import com.example.newsappjetpackcompose.util.ResultWrapper
import com.example.newsappjetpackcompose.util.StringWrapper
import com.example.newsappjetpackcompose.util.ConnectionChecker
import io.reactivex.Observable
import javax.inject.Inject

class NewsServiceImpl @Inject constructor(
    private val connectionChecker: ConnectionChecker,
    private val newsInteractor: NewsInteractor
): NewsService {

    override fun getNews(searchTerm: String, searchType: String): Observable<ResultWrapper<List<ArticleView>>> {
        return if(connectionChecker.isOnline()) {
            newsInteractor.getNews(searchTerm, searchType)
        } else {
            val result: ResultWrapper<List<ArticleView>> = ResultWrapper.Failure(StringWrapper.ResourceStringWrapper(id = R.string.no_internet_connection))
            Observable.just(result)
        }
    }

    override fun checkInputSearch(inputString: String): ResultWrapper<StringWrapper> {
        return if(connectionChecker.isEmptyString(inputString)){
            ResultWrapper.Failure(StringWrapper.ResourceStringWrapper(id = R.string.error_empty_input))
        } else if(connectionChecker.isLessThanThreeCharacters(inputString)) {
            ResultWrapper.Failure(StringWrapper.ResourceStringWrapper(id = R.string.error_not_enough_characters))
        } else {
            ResultWrapper.Success(StringWrapper.SimpleStringWrapper("Input is valid"))
        }
    }

}