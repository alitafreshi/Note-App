package com.alitafreshi.domain

sealed class DataState<T> {

    data class Error<T>(
        val errorMessage: String
    ) : DataState<T>()

    data class Data<T>(
        val data: T? = null
    ) : DataState<T>()

    data class Loading<T>(
        val loadingState: LoadingState = LoadingState.Idle
    ) : DataState<T>()
}