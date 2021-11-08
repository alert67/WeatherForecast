package com.mateuszkukiel.weatherforecast.features.weather.search.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.mateuszkukiel.core.validators.SearchQueryValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchQueryValidator: SearchQueryValidator,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    var query = ""

    private val _isQueryValid: MutableLiveData<Boolean> = MutableLiveData()
    val isQueryValid: LiveData<Boolean> = _isQueryValid

    fun validateInputAndSetQuery(text: String) {
        query = text
        _isQueryValid.value = searchQueryValidator.validate(text)
    }

}