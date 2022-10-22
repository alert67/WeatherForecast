package com.mateuszkukiel.weatherforecast.features.weather.data.local.model

import com.mateuszkukiel.weatherforecast.features.weather.domain.model.Condition

data class ConditionCached (
    val text: String,
    val icon: String
) {
    constructor(condition: Condition): this(
        text = condition.text,
        icon = condition.icon
    )

    fun toCondition() = Condition(
        text = text,
        icon = icon
    )

    companion object
}