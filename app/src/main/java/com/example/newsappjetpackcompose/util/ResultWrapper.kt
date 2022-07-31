package com.example.newsappjetpackcompose.util

sealed class ResultWrapper<T>{
    data class Success<T>(val value: T): ResultWrapper<T>()
    data class Failure<T>(val errorMessage: UIText = UIText.DynamicString("")): ResultWrapper<T>()
}