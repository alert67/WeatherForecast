package com.mateuszkukiel.mock

import com.mateuszkukiel.core.api.models.*
import org.jetbrains.annotations.TestOnly

@TestOnly
fun WeatherResponse.Companion.mock() = WeatherResponse(
    location = LocationRemote.Companion.mock(),
    forecast = ForecastRemote.Companion.mock()
)

@TestOnly
fun LocationRemote.Companion.mock() = LocationRemote(
    name = "name",
    region = "region",
    country = "country",
    lat = 12.12,
    lon = 13.12,
    tzId = "tzId",
    localtimeEpoch = 2,
    localtime = "localtime"
)

@TestOnly
fun ForecastRemote.Companion.mock() = ForecastRemote(
    forecastday = listOf(ForecastDayRemote.Companion.mock(), ForecastDayRemote.Companion.mock(), ForecastDayRemote.Companion.mock())
)

@TestOnly
fun ForecastDayRemote.Companion.mock() = ForecastDayRemote(
    date = "date",
    dateEpoch = 2,
    hour = listOf(HourRemote.Companion.mock(), HourRemote.Companion.mock(), HourRemote.Companion.mock())
)

@TestOnly
fun HourRemote.Companion.mock() = HourRemote(
    timeEpoch = 1,
    time = "time",
    tempC = 2.1,
    tempF = 77.2,
    isDay = 1,
    condition = ConditionRemote.mock(),
    windMph = 12.1,
    windKph = 11.1,
    windDegree = 2,
    windDir = "winDir",
    pressureMb = 12.2,
    pressureIn = 13.1,
    precipMm = 1.2,
    precipIn = 2.1,
    humidity = 1,
    cloud = 2,
    feelslikeC = 2.1,
    feelslikeF = 1.1,
    windchillC = 12.12,
    windchillF = 77.77,
    heatindexC = 12.12,
    heatindexF = 12.12,
    dewpointC = 2.3,
    dewpointF = 1.1,
    willItRain = 2,
    chanceOfRain = 1,
    willItSnow = 2,
    chanceOfSnow = 1,
    visKm = 42.2,
    visMiles = 12.2,
    gustMph = 12.21,
    gustKph = 12.33,
    uv = 22.22
)

@TestOnly
fun ConditionRemote.Companion.mock() = ConditionRemote(
    text = "text",
    icon = "icon_url",
    code = 2
)
