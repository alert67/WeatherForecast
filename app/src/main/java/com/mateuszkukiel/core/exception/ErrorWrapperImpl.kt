package com.mateuszkukiel.core.exception

import retrofit2.HttpException
import java.net.UnknownHostException

class ErrorWrapperImpl : ErrorWrapper {
    override fun wrap(throwable: Throwable): Throwable {
        return when (throwable) {
            is HttpException -> wrapServerError(throwable)
            is UnknownHostException -> NoInternetConnection(throwable)
            else -> throwable
        }
    }

    private fun wrapServerError(httpException: HttpException): Throwable {
        return with(httpException) {
            when (code()) {
                500 -> ServerException.Internal(message())
                400 -> ServerException.BadRequest(message())
                else -> ServerException.Unknown(message())
            }
        }
    }
}