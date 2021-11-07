package com.mateuszkukiel.core.api

import com.mateuszkukiel.core.api.models.WeatherResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    companion object {
        const val BASE_URL = "https://api.weatherapi.com/v1/"
    }

    @GET("forecast.json")
    fun getForecast(
        @Query("q") query: String,
        @Query("days") days: Int = 3
    ): Single<WeatherResponse>
}