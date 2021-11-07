package com.mateuszkukiel.core.api

import io.reactivex.rxjava3.core.Single
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    companion object {
        const val BASE_URL = "https://api.weatherapi.com/v1/"
    }

    @GET("current.json")
    fun getCurrent(
        @Query("q") query: String
    ): Single<Response<ResponseBody>>
}