package com.mateuszkukiel.weatherforecast.features.weather.data.local.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.mateuszkukiel.weatherforecast.features.weather.domain.model.HourWeather

@Entity(
    foreignKeys =[
        ForeignKey(
            entity = DayWeatherCached::class,
            parentColumns = ["id"],
            childColumns = ["dayId"],
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
data class HourWeatherCached(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val dayId: Int,
    val conditionId: Int,
    val localTime: String,
    val tempC: Double,
    val feelsTempC: Double,
    val isDay: Boolean,
    val willItRain:Boolean
) {

    constructor(dayId: Int, hourWeather: HourWeather): this(
        dayId = dayId,
        conditionId = hourWeather.condition.id,
        localTime = hourWeather.localTime,
        tempC = hourWeather.tempC,
        feelsTempC = hourWeather.feelsTempC,
        isDay = hourWeather.isDay,
        willItRain = hourWeather.willItRain
    )

    companion object
}
