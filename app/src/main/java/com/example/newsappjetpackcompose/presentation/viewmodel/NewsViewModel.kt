package com.example.newsappjetpackcompose.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsappjetpackcompose.domain.interactor.NewsInteractor
import com.example.newsappjetpackcompose.presentation.model.ArticleView
import com.example.newsappjetpackcompose.presentation.state.ArticlesUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver

import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsInteractor: NewsInteractor
): ViewModel() {

    private var subscription: Disposable? = null
    private var articles: List<ArticleView> = emptyList()

    private val _uiState = MutableLiveData<ArticlesUIState>()
    val uiState: LiveData<ArticlesUIState>
        get() = _uiState

    fun getNews(searchTerm: String, sortType: String) {

        _uiState.value = ArticlesUIState.Loading

        subscription = newsInteractor.sendData(searchTerm, sortType)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<List<ArticleView>>() {
                override fun onNext(list: List<ArticleView>) {
                    articles = list
                }

                override fun onError(e: Throwable) {
                    _uiState.value = ArticlesUIState.Error(e.message ?: "")
                }

                override fun onComplete() {
                    _uiState.value = ArticlesUIState.Success(articles)
                }
            })
    }

    private fun rxJavaUnsubscribe() {
        if(subscription != null && !subscription!!.isDisposed){
            subscription!!.dispose();
        }
    }

    override fun onCleared() {
        rxJavaUnsubscribe()
        super.onCleared()
    }
}