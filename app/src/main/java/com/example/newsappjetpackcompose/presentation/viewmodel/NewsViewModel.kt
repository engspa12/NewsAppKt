package com.example.newsappjetpackcompose.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.newsappjetpackcompose.R
import com.example.newsappjetpackcompose.domain.interactor.NewsInteractor
import com.example.newsappjetpackcompose.presentation.state.ArticlesUIState
import com.example.newsappjetpackcompose.util.ResultWrapper
import com.example.newsappjetpackcompose.util.UIText
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsInteractor: NewsInteractor
): ViewModel() {

    private var subscription: Disposable? = null

    private val _uiState = MutableStateFlow<ArticlesUIState>(ArticlesUIState.Loading(UIText.ResourceString(id = R.string.loading_news_message)))
    val uiState: StateFlow<ArticlesUIState>
        get() = _uiState

    fun getNews(searchTerm: String, searchType: String) {
        subscription = newsInteractor.sendData(searchTerm, searchType)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                when(result) {
                    is ResultWrapper.Success -> {
                        _uiState.value = ArticlesUIState.Success(result.value)
                    }
                    is ResultWrapper.Failure -> {
                        _uiState.value = ArticlesUIState.Error(result.errorMessage)
                    }
                }
            },{ e ->
                _uiState.value = ArticlesUIState.Error(UIText.ResourceString(id = R.string.error_data_retrieval))
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