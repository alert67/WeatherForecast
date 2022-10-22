package com.mateuszkukiel.weatherforecast.features.weather.data.local.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mateuszkukiel.weatherforecast.features.weather.domain.model.Weather

@Entity
data class WeatherCached (
    @PrimaryKey val query: String,
    @Embedded val location: LocationCached
) {
    constructor(query: String, weather: Weather): this (
        query = query,
        location = LocationCached(weather.location)
    )

    companion object
}
