package com.mateuszkukiel.weatherforecast.features.weather.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mateuszkukiel.weatherforecast.features.weather.domain.model.Condition

@Entity
data class ConditionCached (
    @PrimaryKey
    val id: Int,
    val text: String,
    val icon: String
) {
    constructor(condition: Condition): this(
        id = condition.id,
        text = condition.text,
        icon = condition.icon
    )

    fun toCondition() = Condition(
        id = id,
        text = text,
        icon = icon
    )

    companion object
}