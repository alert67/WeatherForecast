package com.mateuszkukiel.weatherforecast.features.weather.search.presentation.model

sealed class SearchViewActions {
    data class OnSetQuery(val query: String) : SearchViewActions()
    object OnNextButtonClicked : SearchViewActions()
}
