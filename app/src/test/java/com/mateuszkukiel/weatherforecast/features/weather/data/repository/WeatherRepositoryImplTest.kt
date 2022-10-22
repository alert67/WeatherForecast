package com.mateuszkukiel.weatherforecast.features.weather.data.repository

import com.mateuszkukiel.core.api.WeatherApi
import com.mateuszkukiel.core.api.models.WeatherResponse
import com.mateuszkukiel.core.exception.ErrorWrapper
import com.mateuszkukiel.mock.mock
import com.mateuszkukiel.weatherforecast.features.weather.data.local.WeatherDao
import com.mateuszkukiel.weatherforecast.features.weather.data.local.model.HourCached
import com.mateuszkukiel.weatherforecast.features.weather.data.local.model.WeatherCached
import com.mateuszkukiel.weatherforecast.features.weather.data.local.model.WeatherWithHours
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class WeatherRepositoryImplTest {

    @Test
    fun `GIVEN query WHEN get weather THEN get episodes from database`() {
        // given
        val query = "new york"
        val api = mockk<WeatherApi> {
            every { getForecast(query) } returns Single.error(Throwable("error"))
        }

        val weatherLocal1 = WeatherWithHours.mock()
        val weatherLocal2 = WeatherWithHours.mock()
        val weatherDao = mockk<WeatherDao> {
            every { getWeatherByQuery(query) } returns Flowable.just(weatherLocal1, weatherLocal2)
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
        testObserver.assertValues(weatherLocal1.toWeather(), weatherLocal2.toWeather())
    }

    @Test
    fun `GIVEN query WHEN refresh weather THEN fetch weather from remote AND save weather to database`() {
        // given
        val query = "new york"
        val weatherResponse = WeatherResponse.mock()
        val weather = weatherResponse.toWeather()
        val weatherCached = WeatherCached(query, weather)
        val hoursCached = weather.hours.map { hour -> HourCached(query, hour) }
        val api = mockk<WeatherApi> {
            every { getForecast(query) } returns Single.just(weatherResponse)
        }
        val dao = mockk<WeatherDao> {
            every {
                insertWeatherWithHours(
                    weatherCached = weatherCached,
                    hours = hoursCached
                )
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
        verify { dao.insertWeatherWithHours(weatherCached, hoursCached) }
        testObserver.hasSubscription()
        testObserver.assertComplete()
        testObserver.assertNoErrors()
    }
}