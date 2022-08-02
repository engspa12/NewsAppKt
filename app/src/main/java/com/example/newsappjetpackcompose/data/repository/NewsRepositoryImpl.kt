package com.example.newsappjetpackcompose.data.repository

import com.example.newsappjetpackcompose.data.network.datasource.NewsNetworkDataSource
import com.example.newsappjetpackcompose.data.network.model.ArticleNetwork
import com.example.newsappjetpackcompose.data.util.NetworkMapper
import com.example.newsappjetpackcompose.domain.model.ArticleDomain
import com.example.newsappjetpackcompose.domain.repository.NewsRepository
import com.example.newsappjetpackcompose.global.Constants.API_KEY
import com.example.newsappjetpackcompose.global.Constants.CACHE_LIFETIME
import com.example.newsappjetpackcompose.global.Constants.FILTER_RESULTS
import com.example.newsappjetpackcompose.global.Constants.NUMBER_OF_ARTICLES
import io.reactivex.Observable
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsNetworkDataSource: NewsNetworkDataSource,
    private val networkMapper: NetworkMapper<ArticleNetwork, ArticleDomain>
): NewsRepository {

    /*Simulate the data in Cache*/
    private var listArticlesNetwork: List<ArticleNetwork>? = emptyList()
    private var lastTimeStamp: Long = 0

    override fun getNewsData(searchTerm: String, searchType: String): Observable<List<ArticleDomain>> {
        //Map for querying the API
        val parameters = HashMap<String, String>()

        parameters["q"] = searchTerm
        parameters["order-by"] = searchType
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

        val newsApiObservable = newsNetworkDataSource.getNews(queryParams)

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