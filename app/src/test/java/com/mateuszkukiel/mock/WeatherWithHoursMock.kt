package com.mateuszkukiel.mock

import com.mateuszkukiel.weatherforecast.features.weather.data.local.model.*
import org.jetbrains.annotations.TestOnly

@TestOnly
fun WeatherWithHours.Companion.mock() = WeatherWithHours(
    weatherCached = WeatherCached.mock(),
    hours = listOf(
        HourCached.mock(),
        HourCached.mock(),
        HourCached.mock(),
    )
)

@TestOnly
fun WeatherCached.Companion.mock() = WeatherCached(
    query = "new york",
    location = LocationCached.mock()
)

@TestOnly
fun LocationCached.Companion.mock() = LocationCached(
    name = "name",
    country = "new york",
    lat = 12.12,
    lon = 12.11
)

@TestOnly
fun HourCached.Companion.mock() = HourCached(
    hourId = 1,
    query = "new york",
    localTime = "localTime",
    tempC = 12.0,
    feelsTempC = 10.0,
    isDay = true,
    willItRain = true,
    condition = ConditionCached.mock()
)

@TestOnly
fun ConditionCached.Companion.mock() = ConditionCached(
    text = "condition",
    icon = "icon_url"
)