package com.alitafreshi.proto_datastore


import kotlinx.coroutines.flow.Flow

interface AppProtoDataStore<T> {
    suspend fun setValue(savedObj: T)
    fun getValue(): Flow<T>
}

