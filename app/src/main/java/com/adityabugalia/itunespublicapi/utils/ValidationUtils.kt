package com.adityabugalia.itunespublicapi.utils

object ValidationUtils {
    private val FILTER_QUERY_TEXT_MIN_LENGTH = 5

    //check for minimum length
    fun validateSearchInput(inputStr: String): Boolean {
        return inputStr.length >= FILTER_QUERY_TEXT_MIN_LENGTH
    }
}