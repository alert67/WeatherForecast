package com.mateuszkukiel.core.exception

sealed class SearchQueryValidationException(message: String?): Throwable(message) {
    class QueryIsEmpty(message: String?): SearchQueryValidationException(message)
    class MinimumLengthIs3(message: String?): SearchQueryValidationException(message)
    class QueryNotMatch(message: String?): SearchQueryValidationException(message)
}
