package com.mateuszkukiel.core.api.interceptors

import android.content.Context
import com.mateuszkukiel.weatherforecast.R
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class ApiInterceptor @Inject constructor(@ApplicationContext val context: Context): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val apiKey = context.getString(R.string.weather_api_key)
        val originalRequest = chain.request()
        val originalHttpUrl = originalRequest.url

        val newUrl = originalHttpUrl.newBuilder()
            .addQueryParameter("key", apiKey)
            .build()

        val newRequest = originalRequest.newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }

}