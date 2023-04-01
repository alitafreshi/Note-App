package com.alitafreshi.data.datasource.local.datastore

import ir.tafreshiali.ayan_core.util.BottomSheetState

sealed class DataState<T> {

    data class Error<T>(
        val errorMessage: String
    ) : DataState<T>()

    data class Data<T>(
        val data: T? = null
    ) : DataState<T>()

    data class Loading<T>(
        val bottomSheetState: BottomSheetState = BottomSheetState.Idle
    ) : DataState<T>()
}