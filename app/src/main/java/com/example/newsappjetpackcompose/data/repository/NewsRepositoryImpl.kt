package com.example.newsappjetpackcompose.data.repository

import com.example.newsappjetpackcompose.BuildConfig
import com.example.newsappjetpackcompose.data.network.datasource.NewsService
import com.example.newsappjetpackcompose.data.network.model.ArticleNetwork
import com.example.newsappjetpackcompose.data.util.NetworkMapper
import com.example.newsappjetpackcompose.domain.model.ArticleDomain
import com.example.newsappjetpackcompose.domain.repository.NewsRepository
import io.reactivex.Observable
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsService: NewsService,
    private val networkMapper: NetworkMapper<ArticleNetwork, ArticleDomain>
): NewsRepository {

    /*Simulate the data in Cache*/
    private var listArticlesNetwork: List<ArticleNetwork>? = emptyList()
    private var lastTimeStamp: Long = 0

    companion object {
        private const val CACHE_LIFETIME = 20 * 1000
        private const val NUMBER_OF_ARTICLES = "50"
        private const val API_KEY = BuildConfig.API_KEY
        private const val FILTER_RESULTS = "headline,byline,thumbnail"
    }

    override fun getNewsData(searchTerm: String, sortType: String): Observable<List<ArticleDomain>> {
        //Map for querying the API
        val parameters = HashMap<String, String>()

        parameters["q"] = searchTerm
        parameters["order-by"] = sortType
        parameters["show-fields"] = FILTER_RESULTS
        parameters["page-size"] = NUMBER_OF_ARTICLES
        parameters["api-key"] = API_KEY

        val data = getNewsDataFromCache().switchIfEmpty(getNewsDataFromNetwork(parameters))

        return data.concatMap { responseItems ->
            val listDomainItems = responseItems.map {
                networkMapper.mapToDomainModel(it)
            }
            Observable.just(listDomainItems)
        }
    }

    private fun getNewsDataFromNetwork(queryParams: Map<String, String>): Observable<List<ArticleNetwork>> {

        val newsApiObservable = newsService.getNews(queryParams)

        return newsApiObservable.concatMap { newsSearch ->
            Observable.just(newsSearch.response)
        }.concatMap { response ->
            listArticlesNetwork = response.articlesNetwork
            Observable.just(response.articlesNetwork)
        }
    }

    private fun getNewsDataFromCache(): Observable<List<ArticleNetwork>> {
        return if(isUpdated()){
            Observable.just(listArticlesNetwork)
        } else {
            lastTimeStamp = System.currentTimeMillis()
            listArticlesNetwork = emptyList()
            Observable.empty()
        }
    }

    private fun isUpdated(): Boolean {
        return (System.currentTimeMillis() - lastTimeStamp) < CACHE_LIFETIME
    }
}