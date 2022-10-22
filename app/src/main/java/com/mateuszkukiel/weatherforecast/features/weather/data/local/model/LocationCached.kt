package com.mateuszkukiel.weatherforecast.features.weather.data.local.model

import com.mateuszkukiel.weatherforecast.features.weather.domain.model.Location

data class LocationCached(
    val name: String,
    val country: String,
    val lat: Double,
    val lon: Double
) {
    constructor(location: Location) : this(
        name = location.name,
        country = location.country,
        lat = location.lat,
        lon = location.lon
    )

    fun toLocation() = Location(
        name = name,
        country = country,
        lat = lat,
        lon = lon
    )

    companion object
}
