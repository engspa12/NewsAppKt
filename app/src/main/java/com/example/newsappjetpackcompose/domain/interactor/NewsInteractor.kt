package com.example.newsappjetpackcompose.domain.interactor

import com.example.newsappjetpackcompose.data.network.theguardian.Result
import io.reactivex.Observable

interface NewsInteractor {
    fun sendData(searchTerm: String, sortType: String): Observable<Result>
}