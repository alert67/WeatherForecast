package com.mateuszkukiel.core.validators

import javax.inject.Inject

class SearchQueryValidator @Inject constructor() {
    private val onlyLettersRegex = "^[A-Za-z]*$".toRegex()
    private val zipRegex = "^[0-9]{5}(?:-[0-9]{4})?$".toRegex()
    private val latLonRegex = "^([-+]?\\d{1,2}[.]\\d+),\\s*([-+]?\\d{1,3}[.]\\d+)$".toRegex()

    fun validate(text: String): Boolean {
        return if (text.length > 2) {
            when {
                text.matches(onlyLettersRegex) -> true
                text.matches(zipRegex) -> true
                text.matches(latLonRegex) -> true
                else -> false
            }
        } else {
            false
        }
    }
}