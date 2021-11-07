package com.mateuszkukiel.weatherforecast.features.weather.domain

import com.mateuszkukiel.weatherforecast.features.weather.domain.model.Weather
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

interface WeatherRepository {
    fun getWeather(query: String): Observable<Weather>
    fun refreshWeather(query: String): Completable
}