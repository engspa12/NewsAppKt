package com.example.newsappjetpackcompose.data.repository

import com.example.newsappjetpackcompose.BuildConfig
import com.example.newsappjetpackcompose.data.network.NewsService
import com.example.newsappjetpackcompose.data.network.theguardian.Result
import com.example.newsappjetpackcompose.domain.repository.NewsRepository
import io.reactivex.Observable
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsService: NewsService,
): NewsRepository {

    private val results = ArrayList<Result>()
    private var lastTimeStamp: Long = 0

    companion object {
        private const val CACHE_LIFETIME = 20 * 1000
        private const val NUMBER_OF_ARTICLES = "50"
        private const val API_KEY = BuildConfig.API_KEY
        private const val FILTER_RESULTS = "headline,byline,thumbnail"
    }

    override fun getNewsData(searchTerm: String, sortType: String): Observable<Result> {
        //Map for querying the API
        val parameters = HashMap<String, String>()

        parameters["q"] = searchTerm
        parameters["order-by"] = sortType
        parameters["show-fields"] = FILTER_RESULTS
        parameters["page-size"] = NUMBER_OF_ARTICLES
        parameters["api-key"] = API_KEY

        return getNewsDataFromCache().switchIfEmpty(getNewsDataFromNetwork(parameters))
    }

    override fun getNewsDataFromNetwork(queryParams: Map<String, String>): Observable<Result> {

        val newsApiObservable = newsService.getNews(queryParams)

        return newsApiObservable.concatMap { newsSearch ->
            Observable.just(newsSearch.response)
        }.concatMap { response ->
            Observable.fromIterable(response.results)
        }
    }

    override fun getNewsDataFromCache(): Observable<Result> {
        return if(isUpdated()){
            Observable.fromIterable(results)
        } else {
            lastTimeStamp = System.currentTimeMillis()
            results.clear()
            Observable.empty()
        }
    }

    private fun isUpdated(): Boolean {
        return (System.currentTimeMillis() - lastTimeStamp) < CACHE_LIFETIME
    }
}