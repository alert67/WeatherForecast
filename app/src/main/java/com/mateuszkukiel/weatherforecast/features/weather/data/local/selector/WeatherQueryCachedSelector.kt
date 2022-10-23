package com.mateuszkukiel.weatherforecast.features.weather.data.local.selector

import androidx.room.Embedded
import androidx.room.Relation
import com.mateuszkukiel.weatherforecast.features.weather.data.local.model.DayWeatherCached
import com.mateuszkukiel.weatherforecast.features.weather.data.local.model.WeatherQueryCached
import com.mateuszkukiel.weatherforecast.features.weather.domain.model.WeatherQuery

data class WeatherQueryCachedSelector(
    @Embedded
    val weatherQueryCached: WeatherQueryCached,
    @Relation(parentColumn = "query", entityColumn = "query", entity = DayWeatherCached::class)
    val daysWeatherCached: List<DayWeatherCachedSelector>
) {
    fun toWeatherQuery() = WeatherQuery(
        query = weatherQueryCached.query,
        name = weatherQueryCached.name,
        country = weatherQueryCached.name,
        lat = weatherQueryCached.lat,
        lon = weatherQueryCached.lon,
        daysWeather = daysWeatherCached.map { it.toDayWeather() }
    )
    companion object
}
