package com.alitafreshi.data.datasource.local.datastore

import ir.tafreshiali.ayan_core.util.DataState
import kotlinx.coroutines.flow.Flow

interface AppProtoDataStore<T> {
    suspend fun setValue(savedObj: T)
    fun getValue(): Flow<DataState<T>>
}

