package com.example.newsappjetpackcompose.domain.interactor

import com.example.newsappjetpackcompose.R
import com.example.newsappjetpackcompose.domain.repository.NewsRepository
import com.example.newsappjetpackcompose.domain.util.toView
import com.example.newsappjetpackcompose.presentation.model.ArticleView
import com.example.newsappjetpackcompose.util.ResultWrapper
import com.example.newsappjetpackcompose.util.StringWrapper
import io.reactivex.Observable
import javax.inject.Inject

class NewsInteractorImpl @Inject constructor(
    private val newsRepository: NewsRepository
) : NewsInteractor {

    override fun getNews(
        searchTerm: String,
        searchType: String
    ): Observable<ResultWrapper<List<ArticleView>>> {
        return newsRepository.getNewsData(searchTerm, searchType).concatMap { listItemsDomain ->
            val listViewItems = listItemsDomain.map {
                it.toView()
            }

            val result: ResultWrapper<List<ArticleView>> = if (listViewItems.isEmpty()) {
                ResultWrapper.Failure(StringWrapper.ResourceStringWrapper(id = R.string.no_articles_found_message))
            } else {
                ResultWrapper.Success(listViewItems)
            }

            Observable.just(result)
        }.onErrorReturn {
            ResultWrapper.Failure(StringWrapper.ResourceStringWrapper(id = R.string.error_data_retrieval))
        }
    }
}