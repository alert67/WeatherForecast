package com.mateuszkukiel.weatherforecast.features.weather.search.presentation.model

data class SearchViewState(
    val searchQuery: String = "",
    val isNextButtonEnabled: Boolean = false,
    val searchQueryError: String? = null,
    val isLoading: Boolean = false
)
