package com.mateuszkukiel.weatherforecast.features.weather.data.repository

import com.mateuszkukiel.core.api.WeatherApi
import com.mateuszkukiel.core.exception.ErrorWrapper
import com.mateuszkukiel.weatherforecast.features.weather.data.local.WeatherDao
import com.mateuszkukiel.weatherforecast.features.weather.data.local.model.ConditionCached
import com.mateuszkukiel.weatherforecast.features.weather.data.local.model.DayWeatherCached
import com.mateuszkukiel.weatherforecast.features.weather.data.local.model.HourWeatherCached
import com.mateuszkukiel.weatherforecast.features.weather.data.local.model.WeatherQueryCached
import com.mateuszkukiel.weatherforecast.features.weather.domain.WeatherRepository
import com.mateuszkukiel.weatherforecast.features.weather.domain.model.WeatherQuery
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi,
    private val weatherDao: WeatherDao,
    private val errorWrapper: ErrorWrapper
) : WeatherRepository {
    override fun getWeather(query: String): Observable<WeatherQuery> {
        return weatherDao.getWeatherByQuery(query)
            .map { weatherQuerySelector -> weatherQuerySelector.toWeatherQuery() }
            .toObservable()
    }

    override fun refreshWeather(query: String): Completable {
        return getWeatherFromRemote(query)
            .flatMapCompletable { weatherQuery ->
                insertWeatherToLocal(weatherQuery)
            }
    }

    private fun getWeatherFromRemote(query: String): Single<WeatherQuery> {
        return api.getForecast(query)
            .map { weatherResponse -> weatherResponse.toLocationQuery(query) }
            .onErrorResumeNext { throwable ->
                Single.error(errorWrapper.wrap(throwable))
            }
    }

    private fun insertWeatherToLocal(weatherQuery: WeatherQuery): Completable {
        return Completable.fromAction {
            //insert weather query
            val weatherQueryCached = WeatherQueryCached(weatherQuery)
            weatherDao.insertWeatherQuery(weatherQueryCached)

            //insert conditions
            val conditionsCached: MutableList<ConditionCached> = mutableListOf()
            conditionsCached.addAll(weatherQuery.daysWeather.map { ConditionCached(it.condition) })
            conditionsCached.addAll(weatherQuery.daysWeather.flatMap { day ->
                day.hoursWeather.map { hour ->
                    ConditionCached(
                        hour.condition
                    )
                }
            })
            weatherDao.insertConditions(conditionsCached.toSet().toList())

            // insert days with hours
            weatherQuery.daysWeather.forEach { day ->
                val dayCached = DayWeatherCached(weatherQuery.query, day)
                val dayCachedRoomId = weatherDao.insertDayWeather(dayCached).toInt()
                val hoursCached = day.hoursWeather.map { HourWeatherCached(dayCachedRoomId, it) }
                weatherDao.insertHoursWeather(hoursCached)
            }
        }
    }
}