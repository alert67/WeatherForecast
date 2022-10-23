package com.mateuszkukiel.weatherforecast.features.weather.data.local.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.mateuszkukiel.weatherforecast.features.weather.domain.model.DayWeather

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = WeatherQueryCached::class,
            parentColumns = ["query"],
            childColumns = ["query"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ConditionCached::class,
            parentColumns = ["id"],
            childColumns = ["conditionId"],
            onDelete = ForeignKey.NO_ACTION
        )
    ]
)
data class DayWeatherCached(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val query: String,
    val conditionId: Int,
    val localTime: String,
    val tempC: Double,
    val dailyWillItRain: Boolean,
    val dailyChanceOfRain: Int,
) {
    constructor(query: String, dayWeather: DayWeather) : this(
        query = query,
        conditionId = dayWeather.condition.id,
        localTime = dayWeather.localTime,
        tempC = dayWeather.tempC,
        dailyWillItRain = dayWeather.dailyWillItRain,
        dailyChanceOfRain = dayWeather.dailyChanceOfRain,
    )

    companion object
}
