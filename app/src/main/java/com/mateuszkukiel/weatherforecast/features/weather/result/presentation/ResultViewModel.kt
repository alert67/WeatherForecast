package com.mateuszkukiel.weatherforecast.features.weather.result.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.hadilq.liveevent.LiveEvent
import com.mateuszkukiel.core.base.BaseViewModel
import com.mateuszkukiel.core.base.UiState
import com.mateuszkukiel.core.exception.ErrorMapper
import com.mateuszkukiel.weatherforecast.features.weather.domain.GetWeatherForecastUseCase
import com.mateuszkukiel.weatherforecast.features.weather.domain.RefreshWeatherForecastUseCase
import com.mateuszkukiel.weatherforecast.features.weather.domain.model.WeatherQuery
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(
    private val getWeatherForecastUseCase: GetWeatherForecastUseCase,
    private val refreshWeatherForecastUseCase: RefreshWeatherForecastUseCase,
    private val errorMapper: ErrorMapper,
    savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    private var query: String = ""

    private val _uiState: MutableLiveData<UiState> = MutableLiveData(UiState.Idle)
    val uiState: LiveData<UiState> = _uiState

    private val _locationQuery: MutableLiveData<WeatherQuery> = MutableLiveData()
    val locationQuery: LiveData<WeatherQuery> = _locationQuery

    private val _errorMessage: LiveEvent<String> = LiveEvent()
    val errorMessage: LiveData<String> = _errorMessage

    init {
        savedStateHandle.get<String>(ResultFragment.BUNDLE_SEARCH_QUERY_KEY)?.let { query ->
            this.query = query
            getWeatherForecast(query)
            refreshWeatherForecast(query)
        }
    }

    fun refreshData() {
        refreshWeatherForecast(query)
    }

    private fun refreshWeatherForecast(query: String) {
        _uiState.value = UiState.Pending
        refreshWeatherForecastUseCase.execute(query)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onComplete = {
                    _uiState.value = UiState.Idle
                }, onError = {
                    _uiState.value = UiState.Idle
                    _errorMessage.value = errorMapper.map(it)
                }
            ).addTo(disposables)
    }

    private fun getWeatherForecast(query: String) {
        getWeatherForecastUseCase.execute(query)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy { weather ->
                _locationQuery.value = weather
            }.addTo(disposables)
    }
}