package com.mateuszkukiel.weatherforecast.features.weather.domain.model

data class HourWeather(
    val localTime: String,
    val tempC: Double,
    val feelsTempC: Double,
    val isDay: Boolean,
    val willItRain:Boolean,
    val condition: Condition
)
