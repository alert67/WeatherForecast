package com.mateuszkukiel.weatherforecast.features.weather.data.repository

import com.mateuszkukiel.core.api.WeatherApi
import com.mateuszkukiel.core.api.models.WeatherResponse
import com.mateuszkukiel.core.exception.ErrorWrapper
import com.mateuszkukiel.mock.mock
import com.mateuszkukiel.weatherforecast.features.weather.data.local.WeatherDao
import com.mateuszkukiel.weatherforecast.features.weather.data.local.model.ConditionCached
import com.mateuszkukiel.weatherforecast.features.weather.data.local.model.DayWeatherCached
import com.mateuszkukiel.weatherforecast.features.weather.data.local.model.HourWeatherCached
import com.mateuszkukiel.weatherforecast.features.weather.data.local.model.WeatherQueryCached
import com.mateuszkukiel.weatherforecast.features.weather.data.local.selector.WeatherQueryCachedSelector
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import org.junit.jupiter.api.Test

internal class WeatherQueryCachedSelectorRepositoryImplTest {
    @Test
    fun `GIVEN query WHEN get weather THEN get episodes from database`() {
        // given
        val query = "new york"
        val api = mockk<WeatherApi> {
            every { getForecast(query) } returns Single.error(Throwable("error"))
        }

        val weatherQueryCached1 = WeatherQueryCachedSelector.mock()
        val weatherQueryCached2 = WeatherQueryCachedSelector.mock()
        val weatherDao = mockk<WeatherDao> {
            every { getWeatherByQuery(query) } returns Flowable.just(
                weatherQueryCached1,
                weatherQueryCached2
            )
        }
        val errorWrapper = mockk<ErrorWrapper> {
            every { wrap(Throwable()) } returns Throwable()
        }

        val repository = WeatherRepositoryImpl(api, weatherDao, errorWrapper)

        //when
        val testObserver = repository.getWeather(query).test()

        //then
        verify { weatherDao.getWeatherByQuery(query) }
        testObserver.hasSubscription()
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(2)
        testObserver.assertValues(
            weatherQueryCached1.toWeatherQuery(),
            weatherQueryCached2.toWeatherQuery()
        )
    }

    @Test
    fun `GIVEN query WHEN refresh weather THEN fetch weather from remote AND save weather to database`() {
        // given
        val query = "new york"
        val weatherResponse = WeatherResponse.mock()
        val weatherQuery = weatherResponse.toLocationQuery(query)

        val weatherCached = WeatherQueryCached(weatherQuery)
        val conditionsCached: MutableList<ConditionCached> = mutableListOf()
        conditionsCached.addAll(weatherQuery.daysWeather.map { ConditionCached(it.condition) })
        conditionsCached.addAll(weatherQuery.daysWeather.flatMap { day ->
            day.hoursWeather.map { hour ->
                ConditionCached(
                    hour.condition
                )
            }
        })
        val daysCached = weatherQuery.daysWeather.map { DayWeatherCached(query, it) }
        val hoursCached = weatherQuery.daysWeather.flatMap { day ->
            day.hoursWeather.map {
                HourWeatherCached(
                    2,
                    it
                )
            }
        }

        val api = mockk<WeatherApi> {
            every { getForecast(query) } returns Single.just(weatherResponse)
        }
        val dao = mockk<WeatherDao>(relaxUnitFun = true) {
            every {
                insertWeatherQuery(
                    weatherQueryCached = weatherCached
                )
            } returns Unit
            every {
                insertConditions(conditionsCached.toSet().toList())
            } returns Unit
            every {
                insertDayWeather(daysCached.first())
            } returns 0
            every {
                insertHoursWeather(hoursCached)
            } returns Unit
        }
        val errorWrapper = mockk<ErrorWrapper> {
            every { wrap(Throwable()) } returns Throwable()
        }
        val repository = WeatherRepositoryImpl(api, dao, errorWrapper)

        //when
        val testObserver = repository.refreshWeather(query).test()

        //then
        verify { api.getForecast(query) }
        verify { dao.insertWeatherQuery(weatherCached) }
        testObserver.hasSubscription()
        testObserver.assertComplete()
        testObserver.assertNoErrors()
    }
}