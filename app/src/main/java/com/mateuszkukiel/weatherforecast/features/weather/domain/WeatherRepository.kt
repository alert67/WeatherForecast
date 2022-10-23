package com.mateuszkukiel.weatherforecast.features.weather.domain

import com.mateuszkukiel.weatherforecast.features.weather.domain.model.WeatherQuery
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

interface WeatherRepository {
    fun getWeather(query: String): Observable<WeatherQuery>
    fun refreshWeather(query: String): Completable
}