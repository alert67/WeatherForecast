package com.mateuszkukiel.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mateuszkukiel.weatherforecast.features.weather.data.local.WeatherDao
import com.mateuszkukiel.weatherforecast.features.weather.data.local.model.HourCached
import com.mateuszkukiel.weatherforecast.features.weather.data.local.model.WeatherCached

@Database(
    entities = [WeatherCached::class, HourCached::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}