package com.mateuszkukiel.core.api.models

import com.google.gson.annotations.SerializedName
import com.mateuszkukiel.weatherforecast.features.weather.domain.model.Hour

data class ForecastRemote(
    @SerializedName("forecastday") val forecastday: List<ForecastDayRemote>
) {
    fun getHours(): List<Hour> =
        forecastday.flatMap { remoteDay -> remoteDay.hour.map { remoteHour -> remoteHour.toHour() } }

    companion object
}

data class ForecastDayRemote(
    @SerializedName("date") val date: String,
    @SerializedName("date_epoch") val dateEpoch: Int,
    @SerializedName("hour") val hour: List<HourRemote>
) {
    companion object
}

data class HourRemote(
    @SerializedName("time_epoch") val timeEpoch: Int,
    @SerializedName("time") val time: String,
    @SerializedName("temp_c") val tempC: Double,
    @SerializedName("temp_f") val tempF: Double,
    @SerializedName("is_day") val isDay: Int,
    @SerializedName("condition") val condition: ConditionRemote,
    @SerializedName("wind_mph") val windMph: Double,
    @SerializedName("wind_kph") val windKph: Double,
    @SerializedName("wind_degree") val windDegree: Int,
    @SerializedName("wind_dir") val windDir: String,
    @SerializedName("pressure_mb") val pressureMb: Double,
    @SerializedName("pressure_in") val pressureIn: Double,
    @SerializedName("precip_mm") val precipMm: Double,
    @SerializedName("precip_in") val precipIn: Double,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("cloud") val cloud: Int,
    @SerializedName("feelslike_c") val feelslikeC: Double,
    @SerializedName("feelslike_f") val feelslikeF: Double,
    @SerializedName("windchill_c") val windchillC: Double,
    @SerializedName("windchill_f") val windchillF: Double,
    @SerializedName("heatindex_c") val heatindexC: Double,
    @SerializedName("heatindex_f") val heatindexF: Double,
    @SerializedName("dewpoint_c") val dewpointC: Double,
    @SerializedName("dewpoint_f") val dewpointF: Double,
    @SerializedName("will_it_rain") val willItRain: Int,
    @SerializedName("chance_of_rain") val chanceOfRain: Int,
    @SerializedName("will_it_snow") val willItSnow: Int,
    @SerializedName("chance_of_snow") val chanceOfSnow: Int,
    @SerializedName("vis_km") val visKm: Double,
    @SerializedName("vis_miles") val visMiles: Double,
    @SerializedName("gust_mph") val gustMph: Double,
    @SerializedName("gust_kph") val gustKph: Double,
    @SerializedName("uv") val uv: Double
) {
    fun toHour() = Hour(
        localTime = time,
        tempC = tempC,
        feelsTempC = feelslikeC,
        isDay = isDay >= 1,
        willItRain = willItRain >= 1,
        condition = condition.toCondition()
    )

    companion object
}
