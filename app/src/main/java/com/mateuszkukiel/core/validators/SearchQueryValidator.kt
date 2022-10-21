package com.mateuszkukiel.core.validators

import javax.inject.Inject

class SearchQueryValidator @Inject constructor() {
    private val onlyLettersRegex = "^[a-zA-Z]+(?:[\\s-][a-zA-Z]+)*\$".toRegex()
    private val zipRegex = "^[0-9]{5}(?:-[0-9]{4})?$".toRegex()
    private val latLonRegex = "^([-+]?\\d{1,2}[.]\\d+),\\s*([-+]?\\d{1,3}[.]\\d+)$".toRegex()

    fun validate(text: String): Boolean {
        val trimmedText = text.trim()
        return if (trimmedText.length > 2) {
            when {
                trimmedText.matches(onlyLettersRegex) -> true
                trimmedText.matches(zipRegex) -> true
                trimmedText.matches(latLonRegex) -> true
                else -> false
            }
        } else {
            false
        }
    }
}