package com.mateuszkukiel.weatherforecast.features.weather.data.local.model

import androidx.room.Embedded
import androidx.room.Relation
import com.mateuszkukiel.weatherforecast.features.weather.domain.model.Weather

data class WeatherWithHours(
    @Embedded val weatherCached: WeatherCached,
    @Relation(
        parentColumn = "query",
        entityColumn = "query"
    )
    val hours: List<HourCached>
) {
    constructor(query: String, weather: Weather) : this(
        weatherCached = WeatherCached(query, weather),
        hours = weather.hours.map { hour -> HourCached(query, hour) }
    )

    fun toWeather() = Weather(
        location = weatherCached.location.toLocation(),
        hours = hours.map { hourCached -> hourCached.toHour() }
    )
}
