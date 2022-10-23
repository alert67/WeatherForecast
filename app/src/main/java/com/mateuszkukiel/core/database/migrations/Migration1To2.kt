package com.mateuszkukiel.core.database.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class Migration1To2: Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("DROP table `WeatherCached`")
        database.execSQL("DROP table `HourCached`")

        database.execSQL("CREATE TABLE IF NOT EXISTS `WeatherQueryCached` (`query` TEXT NOT NULL, `name` TEXT NOT NULL, `country` TEXT NOT NULL, `lat` REAL NOT NULL, `lon` REAL NOT NULL, PRIMARY KEY(`query`))")
        database.execSQL("CREATE TABLE IF NOT EXISTS `DayWeatherCached` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `query` TEXT NOT NULL, `conditionId` INTEGER NOT NULL, `localTime` TEXT NOT NULL, `tempC` REAL NOT NULL, `dailyWillItRain` INTEGER NOT NULL, `dailyChanceOfRain` INTEGER NOT NULL, FOREIGN KEY(`query`) REFERENCES `WeatherQueryCached`(`query`) ON DELETE CASCADE , FOREIGN KEY(`conditionId`) REFERENCES `ConditionCached`(`id`) ON DELETE NO ACTION )")
        database.execSQL("CREATE TABLE IF NOT EXISTS `HourWeatherCached` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `dayId` INTEGER NOT NULL, `conditionId` INTEGER NOT NULL, `localTime` TEXT NOT NULL, `tempC` REAL NOT NULL, `feelsTempC` REAL NOT NULL, `isDay` INTEGER NOT NULL, `willItRain` INTEGER NOT NULL, FOREIGN KEY(`dayId`) REFERENCES `DayWeatherCached`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE , FOREIGN KEY(`conditionId`) REFERENCES `ConditionCached`(`id`) ON UPDATE NO ACTION ON DELETE NO ACTION )")
        database.execSQL("CREATE TABLE IF NOT EXISTS `ConditionCached` (`id` INTEGER NOT NULL, `text` TEXT NOT NULL, `icon` TEXT NOT NULL, PRIMARY KEY(`id`))")
    }
}