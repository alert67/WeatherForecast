package com.mateuszkukiel.weatherforecast.features.weather.data.local

import androidx.room.*
import com.mateuszkukiel.weatherforecast.features.weather.data.local.model.HourCached
import com.mateuszkukiel.weatherforecast.features.weather.data.local.model.WeatherCached
import com.mateuszkukiel.weatherforecast.features.weather.data.local.model.WeatherWithHours
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

@Dao
abstract class WeatherDao {

    @Transaction
    @Query("SELECT * FROM WeatherCached WHERE `query` = :query")
    abstract fun getWeatherByQuery(query: String): Flowable<WeatherWithHours>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertWeatherWithHours(weatherCached: WeatherCached, hours: List<HourCached>)
}