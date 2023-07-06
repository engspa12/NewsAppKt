package com.example.newsappjetpackcompose.util

sealed class ResultWrapper<out T,out U>{
    data class Success<T>(val value: T): ResultWrapper<T, Nothing>()
    data class Failure<U>(val error: U): ResultWrapper<Nothing, U>()
}