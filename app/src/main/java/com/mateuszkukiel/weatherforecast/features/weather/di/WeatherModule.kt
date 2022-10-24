package com.mateuszkukiel.weatherforecast.features.weather.di

import com.mateuszkukiel.weatherforecast.features.weather.data.repository.WeatherRepositoryImpl
import com.mateuszkukiel.weatherforecast.features.weather.domain.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class WeatherModule {

    @Binds
    abstract fun bindWeatherRepository(repositoryImpl: WeatherRepositoryImpl): WeatherRepository

}