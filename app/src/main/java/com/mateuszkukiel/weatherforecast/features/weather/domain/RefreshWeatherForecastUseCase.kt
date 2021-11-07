package com.mateuszkukiel.weatherforecast.features.weather.domain

import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class RefreshWeatherForecastUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {
    fun execute(query: String) : Completable = weatherRepository.refreshWeather(query)
}