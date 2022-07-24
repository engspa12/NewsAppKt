package com.example.newsappjetpackcompose.domain.interactor

import com.example.newsappjetpackcompose.domain.model.ArticleDomain
import com.example.newsappjetpackcompose.domain.repository.NewsRepository
import com.example.newsappjetpackcompose.presentation.model.ArticleView
import com.example.newsappjetpackcompose.util.FrameworkHelper
import com.example.newsappjetpackcompose.util.ResultWrapper
import io.reactivex.Observable
import javax.inject.Inject

class NewsInteractorImpl @Inject constructor(
    private val newsRepository: NewsRepository,
    private val frameworkHelper: FrameworkHelper
): NewsInteractor {

    override fun sendData(searchTerm: String, sortType: String): Observable<ResultWrapper<List<ArticleView>>> {
        return if(frameworkHelper.isOnline()) {
            newsRepository.getNewsData(searchTerm, sortType).concatMap { listItemsDomain ->
                val listViewItems = listItemsDomain.map {
                    it.toView()
                }
                val result: ResultWrapper<List<ArticleView>> = ResultWrapper.Success(listViewItems)
                Observable.just(result)
            }.onErrorReturn {
                ResultWrapper.Failure("There was a problem retrieving the data")
            }
        } else {
            Observable.error(Throwable(frameworkHelper.getNoInternetMessage()))
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