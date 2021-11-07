package com.mateuszkukiel.weatherforecast.features.weather.data.local.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.mateuszkukiel.weatherforecast.features.weather.domain.model.Hour

@Entity(
    foreignKeys =[
        ForeignKey(
            entity = WeatherCached::class,
            parentColumns = ["query"],
            childColumns = ["query"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class HourCached(
    @PrimaryKey(autoGenerate = true)
    val hourId: Long = 0,
    val query: String,
    val localTime: String,
    val tempC: Double,
    val feelsTempC: Double,
    val isDay: Boolean,
    val willItRain:Boolean,
    @Embedded val condition: ConditionCached
) {

    constructor(query: String, hour: Hour): this(
        localTime = hour.localTime,
        query = query,
        tempC = hour.tempC,
        feelsTempC = hour.feelsTempC,
        isDay = hour.isDay,
        willItRain = hour.willItRain,
        condition = ConditionCached(hour.condition)
    )

    fun toHour() = Hour(
        localTime = localTime,
        tempC = tempC,
        feelsTempC = feelsTempC,
        isDay = isDay,
        willItRain = willItRain,
        condition = condition.toCondition()
    )
}
