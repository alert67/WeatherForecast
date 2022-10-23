package com.mateuszkukiel.core.api.models

import com.google.gson.annotations.SerializedName
import com.mateuszkukiel.weatherforecast.features.weather.domain.model.Condition

data class ConditionRemote(
    @SerializedName("code")
    val code: Int,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("text")
    val text: String
) {
    companion object

    fun toCondition() = Condition(
        id = code,
        icon = icon,
        text = text
    )
}