package com.alitafreshi.domain

sealed interface DataState<out T> {

    data class Error<T>(
        val errorMessage: String
    ) : DataState<T>

    data class Data<T>(
        val data: T? = null
    ) : DataState<T>

    object Loading : DataState<Nothing>
}