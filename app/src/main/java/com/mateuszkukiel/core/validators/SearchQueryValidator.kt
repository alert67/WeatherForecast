package com.mateuszkukiel.core.validators

import com.mateuszkukiel.core.exception.SearchQueryValidationException
import javax.inject.Inject

class SearchQueryValidator @Inject constructor() {
    private val cityRegex = "^[a-zA-Z]+(?:[\\s-][a-zA-Z]+)*\$".toRegex()
    private val zipRegex = "^[0-9]{5}(?:-[0-9]{4})?$".toRegex()
    private val latLonRegex = "^([-+]?\\d{1,2}[.]\\d+),\\s*([-+]?\\d{1,3}[.]\\d+)$".toRegex()

    @Throws(SearchQueryValidationException::class)
    fun validate(text: String): Boolean {
        if (text.isBlank()) throw SearchQueryValidationException.QueryIsEmpty("Query is blank")
        if (text.length < 3) throw SearchQueryValidationException.MinimumLengthIs3("Length is less than 3")
        if (text.matches(cityRegex) || text.matches(zipRegex) || text.matches(latLonRegex)) return true else {
            throw SearchQueryValidationException.QueryNotMatch("Query does not match any regex")
        }
    }
}