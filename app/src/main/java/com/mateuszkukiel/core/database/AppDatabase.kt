package com.mateuszkukiel.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mateuszkukiel.weatherforecast.features.weather.data.local.WeatherDao
import com.mateuszkukiel.weatherforecast.features.weather.data.local.model.ConditionCached
import com.mateuszkukiel.weatherforecast.features.weather.data.local.model.DayWeatherCached
import com.mateuszkukiel.weatherforecast.features.weather.data.local.model.HourWeatherCached
import com.mateuszkukiel.weatherforecast.features.weather.data.local.model.WeatherQueryCached

@Database(
    version = AppDatabase.DATABASE_VERSION,
    entities = [WeatherQueryCached::class, DayWeatherCached::class, HourWeatherCached::class, ConditionCached::class],
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao

    companion object {
        const val DATABASE_VERSION = 2
    }
}