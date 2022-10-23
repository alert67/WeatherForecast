package com.mateuszkukiel.weatherforecast.features.weather.domain

import com.mateuszkukiel.weatherforecast.features.weather.domain.model.WeatherQuery
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GetWeatherForecastUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {
    fun execute(query: String): Observable<WeatherQuery> = weatherRepository.getWeather(query)
}