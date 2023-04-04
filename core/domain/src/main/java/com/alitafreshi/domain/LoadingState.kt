package com.alitafreshi.domain

sealed class LoadingState {
    object Loading : LoadingState()
    object Idle : LoadingState()
}