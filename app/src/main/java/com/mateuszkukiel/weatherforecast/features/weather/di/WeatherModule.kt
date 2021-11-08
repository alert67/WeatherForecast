package com.mateuszkukiel.weatherforecast.features.weather.di

import com.mateuszkukiel.core.api.WeatherApi
import com.mateuszkukiel.core.exception.ErrorWrapper
import com.mateuszkukiel.weatherforecast.features.weather.data.local.WeatherDao
import com.mateuszkukiel.weatherforecast.features.weather.data.repository.WeatherRepositoryImpl
import com.mateuszkukiel.weatherforecast.features.weather.domain.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object WeatherModule {

    @Provides
    fun provideWeatherRepository(
        api: WeatherApi,
        weatherDao: WeatherDao,
        errorWrapper: ErrorWrapper
    ): WeatherRepository =
        WeatherRepositoryImpl(api, weatherDao, errorWrapper)

}