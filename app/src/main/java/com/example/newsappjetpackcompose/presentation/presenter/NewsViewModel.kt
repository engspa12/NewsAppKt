package com.example.newsappjetpackcompose.presentation.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsappjetpackcompose.data.network.theguardian.Result
import com.example.newsappjetpackcompose.domain.interactor.NewsInteractor
import com.example.newsappjetpackcompose.domain.model.Article
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
    private var articles = ArrayList<Article>()

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?>
        get() = _errorMessage

    private val _listNewsArticles = MutableLiveData<List<Article>?>()
    val listNewsArticles: LiveData<List<Article>?>
        get() = _listNewsArticles

    fun getNews(searchTerm: String, sortType: String) {
        subscription = newsInteractor.sendData(searchTerm, sortType)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<Result>() {
                override fun onNext(result: Result) {
                    val sectionName = result.sectionName
                    val webUrl = result.webUrl
                    val publicationDate = result.webPublicationDate.substring(0, 10)

                    val fields = result.fields

                    val author = if(fields.byline.isNullOrEmpty()) "Unknown Author" else fields.byline
                    val webTitle = if(fields.headline.isNullOrEmpty()) "Unknown Title" else fields.headline
                    val thumbnailUrl = if(fields.thumbnail.isNullOrEmpty()) "No image available" else fields.thumbnail

                    articles.add(Article(webTitle, sectionName, author, publicationDate, webUrl, thumbnailUrl))
                }

                override fun onError(e: Throwable) {
                    _errorMessage.value = "An error occurred during data retrieval"
                }

                override fun onComplete() {
                    _listNewsArticles.value = articles
                }
            })
    }

    fun rxJavaUnsubscribe() {
        if(subscription != null && !subscription!!.isDisposed){
            subscription!!.dispose();
        }
    }
}