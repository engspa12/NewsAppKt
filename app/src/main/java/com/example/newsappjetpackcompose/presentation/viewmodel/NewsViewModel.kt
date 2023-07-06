package com.example.newsappjetpackcompose.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.newsappjetpackcompose.domain.service.NewsService
import com.example.newsappjetpackcompose.domain.util.NewsResponseError
import com.example.newsappjetpackcompose.presentation.state.ArticlesUIState
import com.example.newsappjetpackcompose.util.ResultWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsService: NewsService
): ViewModel() {

    private var subscription: Disposable? = null

    private val _uiState = MutableStateFlow<ArticlesUIState>(ArticlesUIState.Loading)
    val uiState: StateFlow<ArticlesUIState>
        get() = _uiState

    fun getNews(searchTerm: String, searchType: String) {
        subscription = newsService.getNews(searchTerm, searchType)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                when(result) {
                    is ResultWrapper.Success -> {
                        _uiState.value = ArticlesUIState.Success(result.value)
                    }
                    is ResultWrapper.Failure -> {
                        processError(result.error)
                    }
                }
            },{
                _uiState.value = ArticlesUIState.GenericError
            })
    }

    private fun processError(error: NewsResponseError) {
        _uiState.value = when(error){
            NewsResponseError.GENERIC_ERROR -> ArticlesUIState.GenericError
            NewsResponseError.NO_INTERNET_CONNECTION -> ArticlesUIState.NoConnectionError
            NewsResponseError.NO_ARTICLES_FOUND_WITH_SEARCH_TERM -> ArticlesUIState.NoArticlesFoundError
        }
    }

    private fun rxJavaUnsubscribe() {
        if(subscription != null && !subscription!!.isDisposed){
            subscription!!.dispose()
        }
    }

    override fun onCleared() {
        rxJavaUnsubscribe()
        super.onCleared()
    }
}