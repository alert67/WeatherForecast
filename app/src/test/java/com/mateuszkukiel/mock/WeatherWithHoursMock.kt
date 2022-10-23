package com.mateuszkukiel.mock

import com.mateuszkukiel.weatherforecast.features.weather.data.local.model.*
import com.mateuszkukiel.weatherforecast.features.weather.data.local.selector.DayWeatherCachedSelector
import com.mateuszkukiel.weatherforecast.features.weather.data.local.selector.HourWeatherCachedSelector
import com.mateuszkukiel.weatherforecast.features.weather.data.local.selector.WeatherQueryCachedSelector
import org.jetbrains.annotations.TestOnly

@TestOnly
fun WeatherQueryCachedSelector.Companion.mock() = WeatherQueryCachedSelector(
    weatherQueryCached = WeatherQueryCached.Companion.mock(),
    daysWeatherCached = listOf(
        DayWeatherCachedSelector.Companion.mock(),
        DayWeatherCachedSelector.Companion.mock()
    )
)

@TestOnly
fun WeatherQueryCached.Companion.mock() = WeatherQueryCached(
    query = "new york",
    name = "name",
    country = "country",
    lat = 12.12,
    lon = 12.32
)

@TestOnly
fun DayWeatherCachedSelector.Companion.mock() = DayWeatherCachedSelector(
    dayWeatherCached = DayWeatherCached.Companion.mock(),
    conditionCached = ConditionCached.Companion.mock(),
    hoursWeatherCached = listOf(
        HourWeatherCachedSelector.Companion.mock(),
        HourWeatherCachedSelector.Companion.mock(),
        HourWeatherCachedSelector.Companion.mock()
    )
)

@TestOnly
fun DayWeatherCached.Companion.mock() = DayWeatherCached(
    id = 2,
    query = "new york",
    conditionId = 2,
    localTime = "localTime",
    tempC = 12.12,
    dailyWillItRain = true,
    dailyChanceOfRain = 4
)

@TestOnly
fun HourWeatherCachedSelector.Companion.mock() = HourWeatherCachedSelector(
    hourWeatherCached = HourWeatherCached.Companion.mock(),
    conditionCached = ConditionCached.Companion.mock()
)

@TestOnly
fun HourWeatherCached.Companion.mock() = HourWeatherCached(
    id = 1,
    dayId = 2,
    localTime = "localTime",
    tempC = 12.0,
    feelsTempC = 10.0,
    isDay = true,
    willItRain = true,
    conditionId = 2,
)

@TestOnly
fun ConditionCached.Companion.mock() = ConditionCached(
    text = "condition",
    icon = "icon_url",
    id = 2
)
