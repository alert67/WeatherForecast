package com.mateuszkukiel.weatherforecast.features.weather.search.presentation.model

sealed class SearchViewEvents{
    data class GoToResultScreen(val query: String): SearchViewEvents()
}
