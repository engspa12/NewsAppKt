package com.example.newsappjetpackcompose.domain.util

class InputValidator {

    companion object {
        const val UPPER_LIMIT = 3
    }

    fun isEmptyString(inputString: String): Boolean {
        return inputString.trim().isEmpty()
    }

    fun isLessThanThreeCharacters(inputString: String): Boolean {
        return inputString.trim().length < UPPER_LIMIT
    }
}