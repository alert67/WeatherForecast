package com.mateuszkukiel.core.base

sealed class UiState {
    object Idle: UiState()
    object Pending: UiState()
}
