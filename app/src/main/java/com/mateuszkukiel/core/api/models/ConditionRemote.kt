package com.mateuszkukiel.core.api.models

import com.google.gson.annotations.SerializedName
import com.mateuszkukiel.weatherforecast.features.weather.domain.model.Condition

data class ConditionRemote(
    @SerializedName("text") val text: String,
    @SerializedName("icon") val icon: String,
    @SerializedName("code") val code: Int
) {
    fun toCondition() = Condition(
        text = text,
        icon = icon
    )

    companion object
}