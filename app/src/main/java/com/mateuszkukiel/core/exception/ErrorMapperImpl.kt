package com.mateuszkukiel.core.exception

import android.content.Context
import androidx.annotation.StringRes
import com.mateuszkukiel.weatherforecast.R

class ErrorMapperImpl(private val context: Context) : ErrorMapper {
    override fun map(throwable: Throwable): String {
        return when (throwable) {
            is ServerException -> mapServerException(throwable)
            is NoInternetConnection -> getMessage(R.string.error_no_internet_connection)
            else -> getMessage(R.string.error_unknown)
        }
    }

    private fun mapServerException(serverException: ServerException): String {
        return when (serverException) {
            is ServerException.Internal -> getMessage(R.string.error_internal)
            is ServerException.BadRequest -> getMessage(R.string.error_bad_request)
            else -> getMessage(R.string.error_unknown)
        }
    }

    private fun getMessage(@StringRes stringRes: Int) = context.getString(stringRes)
}