package com.example.newsappjetpackcompose.domain.interactor

import com.example.newsappjetpackcompose.domain.repository.NewsRepository
import com.example.newsappjetpackcompose.domain.util.NewsResponseError
import com.example.newsappjetpackcompose.domain.util.toView
import com.example.newsappjetpackcompose.presentation.model.ArticleView
import com.example.newsappjetpackcompose.util.ResultWrapper
import io.reactivex.Observable
import javax.inject.Inject

class NewsInteractorImpl @Inject constructor(
    private val newsRepository: NewsRepository
) : NewsInteractor {

    override fun getNews(
        searchTerm: String,
        searchType: String
    ): Observable<ResultWrapper<List<ArticleView>, NewsResponseError>> {
        return newsRepository.getNewsData(searchTerm, searchType).concatMap { listItemsDomain ->
            val listViewItems = listItemsDomain.map {
                it.toView()
            }

            val result: ResultWrapper<List<ArticleView>, NewsResponseError> = if (listViewItems.isEmpty()) {
                ResultWrapper.Failure(error = NewsResponseError.NO_ARTICLES_FOUND_WITH_SEARCH_TERM)
            } else {
                ResultWrapper.Success(listViewItems)
            }

            Observable.just(result)
        }.onErrorReturn {
            ResultWrapper.Failure(error = NewsResponseError.GENERIC_ERROR)
        }
    }
}