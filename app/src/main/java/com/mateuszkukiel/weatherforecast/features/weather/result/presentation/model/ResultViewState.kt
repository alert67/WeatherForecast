package com.mateuszkukiel.weatherforecast.features.weather.result.presentation.model

import com.mateuszkukiel.weatherforecast.features.weather.domain.model.WeatherQuery

data class ResultViewState(
    val weatherQuery: WeatherQuery? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
