package com.mateuszkukiel.weatherforecast.features.weather.data.repository

import com.mateuszkukiel.core.api.WeatherApi
import com.mateuszkukiel.core.exception.ErrorWrapper
import com.mateuszkukiel.weatherforecast.features.weather.data.local.WeatherDao
import com.mateuszkukiel.weatherforecast.features.weather.data.local.model.HourCached
import com.mateuszkukiel.weatherforecast.features.weather.data.local.model.WeatherCached
import com.mateuszkukiel.weatherforecast.features.weather.data.local.model.WeatherWithHours
import com.mateuszkukiel.weatherforecast.features.weather.domain.WeatherRepository
import com.mateuszkukiel.weatherforecast.features.weather.domain.model.Weather
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi,
    private val weatherDao: WeatherDao,
    private val errorWrapper: ErrorWrapper
) : WeatherRepository {
    override fun getWeather(query: String): Observable<Weather> {
        return weatherDao.getWeatherByQuery(query)
            .map { weatherWithHours -> weatherWithHours.toWeather() }
            .toObservable()
    }

    override fun refreshWeather(query: String): Completable {
        return Completable.fromObservable(getWeatherFromRemote(query)
            .doOnNext { weather ->
                insertWeatherToLocal(query, weather)
            })
    }

    private fun getWeatherFromRemote(query: String): Observable<Weather> {
        return api.getForecast(query)
            .map { weatherResponse -> weatherResponse.toWeather() }
            .toObservable()
            .onErrorResumeNext { throwable ->
                Observable.error(errorWrapper.wrap(throwable))
            }
    }

    private fun insertWeatherToLocal(query: String, weather: Weather) {
        weatherDao.insertWeatherWithHours(
            WeatherCached(query, weather),
            weather.hours.map { hour -> HourCached(query, hour) })
    }
}