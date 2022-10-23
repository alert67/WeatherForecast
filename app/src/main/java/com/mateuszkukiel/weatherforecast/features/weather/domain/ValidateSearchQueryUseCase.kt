package com.mateuszkukiel.weatherforecast.features.weather.domain

import com.mateuszkukiel.core.validators.SearchQueryValidator
import io.reactivex.rxjava3.core.Single
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ValidateSearchQueryUseCase @Inject constructor(
    private val searchQueryValidator: SearchQueryValidator,
) {
    operator fun invoke(query: String): Single<Boolean> {
        return Single.fromCallable {
            searchQueryValidator.validate(query)
        }
    }
}