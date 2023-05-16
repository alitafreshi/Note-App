package com.alitafreshi.proto_datastore


import androidx.datastore.core.DataStore
import com.alitafreshi.data.qualifier.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class AppProtoDataStoreImpl<T>(
    private val dataStore: DataStore<T>,
    @IoDispatcher val ioDispatcher: CoroutineDispatcher
) : AppProtoDataStore<T> {

    override suspend fun setValue(savedObj: T) {
        dataStore.updateData { savedObj }
    }

    override fun getValue(): Flow<T> = dataStore.data.flowOn(ioDispatcher)
}