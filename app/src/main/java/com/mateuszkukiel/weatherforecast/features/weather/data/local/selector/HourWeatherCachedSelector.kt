package com.mateuszkukiel.weatherforecast.features.weather.data.local.selector

import androidx.room.Embedded
import androidx.room.Relation
import com.mateuszkukiel.weatherforecast.features.weather.data.local.model.ConditionCached
import com.mateuszkukiel.weatherforecast.features.weather.data.local.model.HourWeatherCached
import com.mateuszkukiel.weatherforecast.features.weather.domain.model.HourWeather

data class HourWeatherCachedSelector(
    @Embedded
    val hourWeatherCached: HourWeatherCached,
    @Relation(parentColumn = "conditionId", entityColumn = "id", entity = ConditionCached::class)
    val conditionCached: ConditionCached
) {
    fun toHourWeather() = HourWeather(
        localTime = hourWeatherCached.localTime,
        tempC = hourWeatherCached.tempC,
        feelsTempC = hourWeatherCached.feelsTempC,
        isDay = hourWeatherCached.isDay,
        willItRain = hourWeatherCached.willItRain,
        condition = conditionCached.toCondition()
    )

    companion object
}
