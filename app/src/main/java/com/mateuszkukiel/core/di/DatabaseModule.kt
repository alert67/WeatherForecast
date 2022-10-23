package com.mateuszkukiel.core.di

import android.content.Context
import androidx.room.Room
import com.mateuszkukiel.core.database.AppDatabase
import com.mateuszkukiel.core.database.migrations.Migration1To2
import com.mateuszkukiel.weatherforecast.features.weather.data.local.WeatherDao

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    
    @Provides
    fun provideWeatherDao(appDatabase: AppDatabase): WeatherDao {
        return appDatabase.weatherDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "AppDatabase"
        ).addMigrations(Migration1To2()).build()
    }
}