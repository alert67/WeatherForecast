package com.mateuszkukiel.weatherforecast.features.weather.search.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hadilq.liveevent.LiveEvent
import com.mateuszkukiel.core.base.BaseViewModel
import com.mateuszkukiel.core.exception.ErrorMapper
import com.mateuszkukiel.weatherforecast.features.weather.domain.ValidateSearchQueryUseCase
import com.mateuszkukiel.weatherforecast.features.weather.search.presentation.model.SearchViewActions
import com.mateuszkukiel.weatherforecast.features.weather.search.presentation.model.SearchViewEvents
import com.mateuszkukiel.weatherforecast.features.weather.search.presentation.model.SearchViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchQueryUseCase: ValidateSearchQueryUseCase,
    private val errorMapper: ErrorMapper
) : BaseViewModel() {

    private val _viewState: MutableLiveData<SearchViewState> = MutableLiveData(SearchViewState())
    val viewState: LiveData<SearchViewState> = _viewState

    private val _viewEvents: LiveEvent<SearchViewEvents> = LiveEvent()
    val viewEvents: LiveData<SearchViewEvents> = _viewEvents

    private val queryQueue: PublishSubject<String> = PublishSubject.create()

    init {
        observeQueryQueue()
    }

    fun onAction(action: SearchViewActions) {
        when (action) {
            SearchViewActions.OnNextButtonClicked -> onNextButtonClicked()
            is SearchViewActions.OnSetQuery -> onSetQuery(action.query)
        }
    }

    private fun onSetQuery(text: String) {
        val query = text.trim().lowercase(Locale.ROOT)
        queryQueue.onNext(query)
    }

    private fun onNextButtonClicked() {
        viewState.value?.let { state ->
            _viewEvents.value = SearchViewEvents.GoToResultScreen(state.searchQuery)
        }
    }

    private fun observeQueryQueue() {
        queryQueue
            .flatMap {
                _viewState.value?.let { state ->
                    _viewState.value =
                        state.copy(searchQuery = it, isLoading = true, isNextButtonEnabled = false)
                }
                Observable.just(it)
            }
            .observeOn(Schedulers.io())
            .debounce(300, TimeUnit.MILLISECONDS)
            .flatMap {
                searchQueryUseCase(it).toObservable().materialize()
                    .filter { notification -> !notification.isOnComplete }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = { notification ->
                    val isValid = notification.value == true
                    _viewState.value?.let { state ->
                        _viewState.value = state.copy(
                            isLoading = false,
                            isNextButtonEnabled = isValid,
                            searchQueryError = notification.error?.let { errorMapper.map(it) }
                        )
                    }
                },
                onError = { error ->
                    _viewState.value?.let { state ->
                        _viewState.value = state.copy(
                            isLoading = false,
                            isNextButtonEnabled = false,
                            searchQueryError = errorMapper.map(error)
                        )
                    }
                }
            ).addTo(disposables)
    }
}