package com.mateuszkukiel.weatherforecast.features.weather.domain

import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class RefreshWeatherForecastUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {
    operator fun invoke(query: String): Completable = weatherRepository.refreshWeather(query)
}