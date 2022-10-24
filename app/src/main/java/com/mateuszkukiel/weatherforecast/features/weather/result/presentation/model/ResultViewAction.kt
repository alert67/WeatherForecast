package com.mateuszkukiel.weatherforecast.features.weather.result.presentation.model

sealed class ResultViewAction {
    object OnPullToRefresh: ResultViewAction()
}
