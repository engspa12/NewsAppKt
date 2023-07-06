package com.example.newsappjetpackcompose.domain.service

import com.example.newsappjetpackcompose.domain.interactor.NewsInteractor
import com.example.newsappjetpackcompose.domain.util.ConnectionChecker
import com.example.newsappjetpackcompose.domain.util.InputValidator
import com.example.newsappjetpackcompose.domain.util.NewsInputValidationError
import com.example.newsappjetpackcompose.domain.util.NewsResponseError
import com.example.newsappjetpackcompose.presentation.model.ArticleView
import com.example.newsappjetpackcompose.util.ResultWrapper
import io.reactivex.Observable
import javax.inject.Inject

class NewsServiceImpl @Inject constructor(
    private val connectionChecker: ConnectionChecker,
    private val inputValidator: InputValidator,
    private val newsInteractor: NewsInteractor
): NewsService {

    override fun getNews(searchTerm: String, searchType: String): Observable<ResultWrapper<List<ArticleView>, NewsResponseError>> {
        return if(connectionChecker.isOnline()) {
            newsInteractor.getNews(searchTerm, searchType)
        } else {
            val result: ResultWrapper<List<ArticleView>, NewsResponseError> = ResultWrapper.Failure(NewsResponseError.NO_INTERNET_CONNECTION)
            Observable.just(result)
        }
    }

    override fun checkInputSearch(inputString: String): ResultWrapper<Unit, NewsInputValidationError> {
        return if(inputValidator.isEmptyString(inputString)){
            ResultWrapper.Failure(error = NewsInputValidationError.EMPTY_INPUT)
        } else if(inputValidator.isLessThanThreeCharacters(inputString)) {
            ResultWrapper.Failure(error = NewsInputValidationError.NOT_ENOUGH_CHARACTERS)
        } else {
            ResultWrapper.Success(Unit)
        }
    }

}