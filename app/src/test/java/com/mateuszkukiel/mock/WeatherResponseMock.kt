package com.mateuszkukiel.mock

import com.mateuszkukiel.core.api.models.ConditionRemote
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
    forecastday = listOf(
        ForecastDayRemote.Companion.mock(),
        ForecastDayRemote.Companion.mock(),
        ForecastDayRemote.Companion.mock()
    )
)

@TestOnly
fun ForecastDayRemote.Companion.mock() = ForecastDayRemote(
    date = "date",
    dateEpoch = 2,
    dayRemote = DayRemote.Companion.mock(),
    hour = listOf(
        HourRemote.Companion.mock(),
        HourRemote.Companion.mock(),
        HourRemote.Companion.mock()
    )
)

@TestOnly
fun DayRemote.Companion.mock() = DayRemote(
    avghumidity = 12.12,
    avgtempC = 11.11,
    avgtempF = 77.77,
    avgvisKm = 1.2,
    avgvisMiles = 0.4,
    conditionRemote = ConditionRemote.Companion.mock(),
    dailyChanceOfRain = 1,
    dailyChanceOfSnow = 3,
    dailyWillItRain = 0,
    dailyWillItSnow = 0,
    maxtempC = 12.2,
    maxtempF = 77.77,
    mintempC = 11.2,
    mintempF = 75.75,
    totalsnowCm = 1.2,
    maxwindMph = 12.32,
    maxwindKph = 14.23,
    totalprecipIn = 11.03,
    totalprecipMm = 11.11,
    uv = 4.0
)

@TestOnly
fun HourRemote.Companion.mock() = HourRemote(
    timeEpoch = 1,
    time = "time",
    tempC = 2.1,
    tempF = 77.2,
    isDay = 1,
    conditionRemote = ConditionRemote.Companion.mock(),
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
