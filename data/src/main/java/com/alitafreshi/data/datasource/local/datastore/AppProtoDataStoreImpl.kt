package com.alitafreshi.data.datasource.local.datastore

import androidx.datastore.core.DataStore
import com.alitafreshi.data.qualifier.IoDispatcher
import ir.tafreshiali.ayan_core.util.BottomSheetState
import ir.tafreshiali.ayan_core.util.DataState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*

class AppProtoDataStoreImpl<T>(
    private val dataStore: DataStore<ProtoDataStoreObj<T>>,
    @IoDispatcher val ioDispatcher: CoroutineDispatcher
) : AppProtoDataStore<T> {

    override suspend fun setValue(savedObj: T) {
        dataStore.updateData {
            it.copy(savedObj = savedObj)
        }
    }

    override fun getValue(): Flow<DataState<T>> =
        flow<DataState<T>> {

            dataStore.data.onEach {
                emit(DataState.Data(it.savedObj))
            }.onStart {
                emit(DataState.Loading(bottomSheetState = BottomSheetState.Loading))
            }.flowOn(ioDispatcher).collect()
        }.flowOn(ioDispatcher)

}