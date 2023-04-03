package com.alitafreshi.task_list.presentation.util

import com.alitafreshi.domain.DataState
import com.alitafreshi.state_manager.AppEvents
import com.alitafreshi.state_manager.AppStateManager
import ir.tafreshiali.ayan_core.util.BottomSheetState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.transform

/** using for the logics that needs to be handel different states in activity / application level */
suspend fun <T> Flow<DataState<T>>.handleAndCatchGeneralStates(
    stateManager: AppStateManager
): Flow<T> = transform { dataState ->

    when (dataState) {

        is DataState.Error -> stateManager.emitSuspendAppEvent(
            event = AppEvents.ShowSnackBar(
                message = dataState.errorMessage
            )
        )

        is DataState.Data -> dataState.data?.let { data -> emit(value = data) }

        is DataState.Loading -> stateManager.emitSuspendAppEvent(
            event = AppEvents.UpdateLoadingState(
                state = dataState.bottomSheetState == BottomSheetState.Loading
            )
        )
    }
}.catch { exception ->
    stateManager.emitSuspendAppEvent(
        event = AppEvents.ShowSnackBar(
            message = exception.localizedMessage ?: "An unexpected error occurred"
        )
    )
}