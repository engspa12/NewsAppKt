package com.example.newsappjetpackcompose.domain.interactor

import com.example.newsappjetpackcompose.R
import com.example.newsappjetpackcompose.domain.model.ArticleDomain
import com.example.newsappjetpackcompose.domain.repository.NewsRepository
import com.example.newsappjetpackcompose.presentation.model.ArticleView
import com.example.newsappjetpackcompose.util.ResultWrapper
import com.example.newsappjetpackcompose.util.UIText
import com.example.newsappjetpackcompose.util.Validator
import io.reactivex.Observable
import javax.inject.Inject

class NewsInteractorImpl @Inject constructor(
    private val newsRepository: NewsRepository,
    private val validator: Validator
): NewsInteractor {

    override fun sendData(searchTerm: String, searchType: String): Observable<ResultWrapper<List<ArticleView>>> {
        return if(validator.isOnline()) {
            newsRepository.getNewsData(searchTerm, searchType).concatMap { listItemsDomain ->
                val listViewItems = listItemsDomain.map {
                    it.toView()
                }

                val result: ResultWrapper<List<ArticleView>> = if(listViewItems.isEmpty()){
                    ResultWrapper.Failure(UIText.ResourceString(id = R.string.no_articles_found_message))
                } else {
                    ResultWrapper.Success(listViewItems)
                }

                Observable.just(result)
            }.onErrorReturn {
                ResultWrapper.Failure(UIText.ResourceString(id = R.string.error_data_retrieval))
            }
        } else {
            val result: ResultWrapper<List<ArticleView>> = ResultWrapper.Failure(UIText.ResourceString(id = R.string.no_internet_connection))
            Observable.just(result)
        }
    }
}

fun ArticleDomain.toView(): ArticleView{
    return ArticleView(
        this.title,
        this.sectionName,
        this.author,
        this.releaseDate,
        this.webUrl,
        this.thumbnailUrl
    )
}