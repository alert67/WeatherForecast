package com.mateuszkukiel.weatherforecast.features.weather.domain.model

data class WeatherQuery(
    val query: String,
    val name: String,
    val country: String,
    val lat: Double,
    val lon: Double,
    val daysWeather: List<DayWeather>
)
