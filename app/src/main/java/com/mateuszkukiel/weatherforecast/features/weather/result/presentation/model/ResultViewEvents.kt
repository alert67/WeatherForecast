package com.mateuszkukiel.weatherforecast.features.weather.result.presentation.model

sealed class ResultViewEvents {
    data class ShowError(val message: String) : ResultViewEvents()
}
