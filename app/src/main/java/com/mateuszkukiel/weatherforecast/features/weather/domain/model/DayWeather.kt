package com.mateuszkukiel.weatherforecast.features.weather.domain.model

data class DayWeather(
    val localTime: String,
    val tempC: Double,
    val dailyWillItRain:Boolean,
    val dailyChanceOfRain: Int,
    val condition: Condition,
    val hoursWeather: List<HourWeather>
)
