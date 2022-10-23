package com.mateuszkukiel.weatherforecast.features.weather.data.local

import androidx.room.*
import com.mateuszkukiel.weatherforecast.features.weather.data.local.model.ConditionCached
import com.mateuszkukiel.weatherforecast.features.weather.data.local.model.DayWeatherCached
import com.mateuszkukiel.weatherforecast.features.weather.data.local.model.HourWeatherCached
import com.mateuszkukiel.weatherforecast.features.weather.data.local.model.WeatherQueryCached
import com.mateuszkukiel.weatherforecast.features.weather.data.local.selector.WeatherQueryCachedSelector
import io.reactivex.rxjava3.core.Flowable

@Dao
abstract class WeatherDao {

    @Transaction
    @Query("SELECT * FROM WeatherQueryCached WHERE `query` = :query")
    abstract fun getWeatherByQuery(query: String): Flowable<WeatherQueryCachedSelector>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertWeatherQuery(weatherQueryCached: WeatherQueryCached)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertConditions(conditions: List<ConditionCached>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertDayWeather(dayWeatherCached: DayWeatherCached): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertHoursWeather(hoursWeatherCached: List<HourWeatherCached>)

}