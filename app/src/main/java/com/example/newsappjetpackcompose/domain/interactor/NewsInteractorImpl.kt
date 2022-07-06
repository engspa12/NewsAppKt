package com.example.newsappjetpackcompose.domain.interactor

import com.example.newsappjetpackcompose.domain.model.ArticleDomain
import com.example.newsappjetpackcompose.domain.repository.NewsRepository
import com.example.newsappjetpackcompose.presentation.model.ArticleView
import io.reactivex.Observable
import javax.inject.Inject

class NewsInteractorImpl @Inject constructor(
    private val newsRepository: NewsRepository
): NewsInteractor {

    override fun sendData(searchTerm: String, sortType: String): Observable<List<ArticleView>> {
        return newsRepository.getNewsData(searchTerm, sortType).concatMap { listItemsDomain ->
            val listViewItems = listItemsDomain.map {
                it.toView()
            }
            Observable.just(listViewItems)
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