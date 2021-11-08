package com.mateuszkukiel.core.exception

interface ErrorWrapper {
    fun wrap(throwable: Throwable): Throwable
}