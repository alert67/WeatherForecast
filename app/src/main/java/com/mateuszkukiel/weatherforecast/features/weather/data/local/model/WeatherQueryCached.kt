package com.mateuszkukiel.weatherforecast.features.weather.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mateuszkukiel.weatherforecast.features.weather.domain.model.WeatherQuery

@Entity
data class WeatherQueryCached (
    @PrimaryKey val query: String,
    val name: String,
    val country: String,
    val lat: Double,
    val lon: Double
) {
    constructor(locationQuery: WeatherQuery): this (
        query = locationQuery.query,
        name = locationQuery.name,
        country = locationQuery.country,
        lat = locationQuery.lat,
        lon = locationQuery.lon
    )

    companion object
}
