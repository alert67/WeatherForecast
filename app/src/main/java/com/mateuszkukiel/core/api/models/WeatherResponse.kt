package com.mateuszkukiel.core.api.models

import com.google.gson.annotations.SerializedName
import com.mateuszkukiel.weatherforecast.features.weather.domain.model.WeatherQuery

data class WeatherResponse(
    @SerializedName("location") val location: LocationRemote,
    @SerializedName("forecast") val forecast: ForecastRemote
) {
    fun toLocationQuery(query: String): WeatherQuery = WeatherQuery(
        query = query,
        name = location.name,
        country = location.country,
        lat = location.lat,
        lon = location.lon,
        daysWeather = forecast.forecastday.map { it.toDayWeather() }
    )

    companion object
}