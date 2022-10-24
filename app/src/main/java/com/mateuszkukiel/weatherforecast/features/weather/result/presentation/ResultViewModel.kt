package com.mateuszkukiel.weatherforecast.features.weather.result.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.hadilq.liveevent.LiveEvent
import com.mateuszkukiel.core.base.BaseViewModel
import com.mateuszkukiel.core.exception.ErrorMapper
import com.mateuszkukiel.weatherforecast.features.weather.domain.GetWeatherForecastUseCase
import com.mateuszkukiel.weatherforecast.features.weather.domain.RefreshWeatherForecastUseCase
import com.mateuszkukiel.weatherforecast.features.weather.result.presentation.model.ResultViewAction
import com.mateuszkukiel.weatherforecast.features.weather.result.presentation.model.ResultViewEvents
import com.mateuszkukiel.weatherforecast.features.weather.result.presentation.model.ResultViewState
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

    private val _viewState: MutableLiveData<ResultViewState> = MutableLiveData(ResultViewState())
    val viewState: LiveData<ResultViewState> = _viewState

    private val _viewEvents: LiveEvent<ResultViewEvents> = LiveEvent()
    val viewEvents: LiveData<ResultViewEvents> = _viewEvents

    init {
        savedStateHandle.get<String>(ResultFragment.BUNDLE_SEARCH_QUERY_KEY)?.let { query ->
            this.query = query
            getWeatherForecast(query)
            refreshWeatherForecast(query)
        }
    }

    fun onAction(action: ResultViewAction) {
        when (action) {
            ResultViewAction.OnPullToRefresh -> refreshWeatherForecast(query)
        }
    }

    private fun refreshWeatherForecast(query: String) {
        _viewState.value?.let { state -> _viewState.value = state.copy(isLoading = true) }
        refreshWeatherForecastUseCase(query)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onComplete = {
                    _viewState.value?.let { state ->
                        _viewState.value = state.copy(isLoading = false, errorMessage = null)
                    }
                }, onError = {
                    val errorMessage = errorMapper.map(it)
                    _viewState.value?.let { state ->
                        _viewState.value = state.copy(isLoading = false, errorMessage = errorMessage)
                    }
                    _viewEvents.value = ResultViewEvents.ShowError(errorMessage)
                }
            ).addTo(disposables)
    }

    private fun getWeatherForecast(query: String) {
        getWeatherForecastUseCase(query)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy { weather ->
                _viewState.value?.let { state ->
                    _viewState.value = state.copy(weatherQuery = weather)
                }
            }.addTo(disposables)
    }
}