package com.example.newsappjetpackcompose.util

interface Validator {

    fun isOnline(): Boolean
    fun isEmptyString(inputString: String): Boolean
    fun isMoreThanTwoCharacters(inputString: String): Boolean

}