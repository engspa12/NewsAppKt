package com.example.newsappjetpackcompose.util

interface ConnectionChecker {

    fun isOnline(): Boolean
    fun isEmptyString(inputString: String): Boolean
    fun isLessThanThreeCharacters(inputString: String): Boolean

}