package com.mateuszkukiel.core.exception

interface ErrorMapper {
    fun map(throwable: Throwable): String
}