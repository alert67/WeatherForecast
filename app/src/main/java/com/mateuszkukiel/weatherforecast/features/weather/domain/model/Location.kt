package com.mateuszkukiel.weatherforecast.features.weather.domain.model

data class Location(
    val name: String,
    val country: String,
    val lat: Double,
    val lon: Double
)
