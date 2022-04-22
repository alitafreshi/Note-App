package com.alitafreshi.data.datasource.local.datastore

import kotlinx.coroutines.flow.Flow

interface AppProtoDataStore<T> {
    suspend fun setValue(savedObj: T)
    fun getValue(): Flow<ProtoDataStoreObj<T>>
}

