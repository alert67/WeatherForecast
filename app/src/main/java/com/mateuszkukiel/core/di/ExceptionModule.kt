package com.mateuszkukiel.core.di

import android.content.Context
import com.mateuszkukiel.core.exception.ErrorMapper
import com.mateuszkukiel.core.exception.ErrorMapperImpl
import com.mateuszkukiel.core.exception.ErrorWrapper
import com.mateuszkukiel.core.exception.ErrorWrapperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ExceptionModule {

    @Provides
    fun providesErrorWrapper(): ErrorWrapper = ErrorWrapperImpl()

    @Provides
    fun providesErrorMapper(@ApplicationContext context: Context): ErrorMapper = ErrorMapperImpl(context)
}