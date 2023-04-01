package com.alitafreshi.data.datasource.local.datastore

import androidx.datastore.core.DataStore
import com.alitafreshi.data.qualifier.IoDispatcher
import ir.tafreshiali.ayan_core.util.BottomSheetState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*

class AppProtoDataStoreImpl<T>(
    private val dataStore: DataStore<T>,
    @IoDispatcher val ioDispatcher: CoroutineDispatcher
) : AppProtoDataStore<T> {

    override suspend fun setValue(savedObj: T) {
        dataStore.updateData { savedObj }
    }

    override fun getValue(): Flow<DataState<T>> =
        dataStore.data // Use dataStore.data directly as a flow
            .transform { value ->
                emit(DataState.Data(value))
                emit(DataState.Loading(bottomSheetState = BottomSheetState.Idle))
            }// Emit DataState.Data and DataState.Loading elements for each value emitted by dataStore.data
            .flowOn(ioDispatcher)
            .onStart { emit(DataState.Loading(bottomSheetState = BottomSheetState.Loading)) } // Emit Loading state when the flow starts
            .catch { emit(DataState.Error(errorMessage = "Cant Read Value From ProtoDataStore")) } // Catch any exceptions thrown during the flow and emit an error state

}