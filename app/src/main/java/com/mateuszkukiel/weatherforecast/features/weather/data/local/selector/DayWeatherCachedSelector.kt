package com.mateuszkukiel.weatherforecast.features.weather.data.local.selector

import androidx.room.Embedded
import androidx.room.Relation
import com.mateuszkukiel.weatherforecast.features.weather.data.local.model.ConditionCached
import com.mateuszkukiel.weatherforecast.features.weather.data.local.model.DayWeatherCached
import com.mateuszkukiel.weatherforecast.features.weather.data.local.model.HourWeatherCached
import com.mateuszkukiel.weatherforecast.features.weather.domain.model.DayWeather

data class DayWeatherCachedSelector(
    @Embedded
    val dayWeatherCached: DayWeatherCached,
    @Relation(parentColumn = "conditionId", entityColumn = "id", entity = ConditionCached::class)
    val conditionCached: ConditionCached,
    @Relation(parentColumn = "id", entityColumn = "dayId", entity = HourWeatherCached::class)
    val hoursWeatherCached: List<HourWeatherCachedSelector>
) {
    fun toDayWeather() = DayWeather(
        localTime = dayWeatherCached.localTime,
        tempC = dayWeatherCached.tempC,
        dailyWillItRain = dayWeatherCached.dailyWillItRain,
        dailyChanceOfRain = dayWeatherCached.dailyChanceOfRain,
        condition = conditionCached.toCondition(),
        hoursWeather = hoursWeatherCached.map { it.toHourWeather() }
    )

    companion object
}
