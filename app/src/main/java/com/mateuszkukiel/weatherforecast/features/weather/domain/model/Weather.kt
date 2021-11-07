package com.mateuszkukiel.weatherforecast.features.weather.domain.model

data class Weather(
    val location: Location,
    val hours: List<Hour>
)
