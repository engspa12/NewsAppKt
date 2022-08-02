package com.example.newsappjetpackcompose.global

import com.example.newsappjetpackcompose.BuildConfig

object Constants {

    const val RELEVANCE_SEARCH_TYPE = "relevance"
    const val NEWEST_SEARCH_TYPE = "newest"
    const val BASE_URL = "https://content.guardianapis.com/"
    const val CACHE_LIFETIME = 20 * 1000
    const val NUMBER_OF_ARTICLES = "50"
    const val API_KEY = BuildConfig.API_KEY
    const val FILTER_RESULTS = "headline,byline,thumbnail"

    enum class NavType {
        NAV_MAIN,
        NAV_DETAILS
    }
}