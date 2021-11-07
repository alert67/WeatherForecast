package com.mateuszkukiel.core.api.models

import com.google.gson.annotations.SerializedName
import com.mateuszkukiel.weatherforecast.features.weather.domain.model.Weather

data class WeatherResponse(
    @SerializedName("location") val location: LocationRemote,
    @SerializedName("forecast") val forecast: ForecastRemote
) {
    fun toWeather(): Weather = Weather(
        location = location.toLocation(),
        hours = forecast.getHours()
    )
}