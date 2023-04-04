package com.alitafreshi.task_list.presentation.util

import com.alitafreshi.domain.DataState
import com.alitafreshi.domain.LoadingState
import com.alitafreshi.state_manager.AppUiEffects
import com.alitafreshi.state_manager.AppStateManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.transform

/** using for the logics that needs to be handel different states in activity / application level */
suspend fun <T> Flow<DataState<T>>.handleAndCatchGeneralStates(
    stateManager: AppStateManager
): Flow<T> = transform { dataState ->

    when (dataState) {

        is DataState.Error -> stateManager.emitSuspendAppUiEffect(
            uiEffect = AppUiEffects.ShowSnackBar(
                message = dataState.errorMessage
            )
        )

        is DataState.Data -> dataState.data?.let { data -> emit(value = data) }

        is DataState.Loading -> stateManager.emitSuspendAppUiEffect(
            uiEffect = AppUiEffects.UpdateLoadingState(
                state = dataState.loadingState == LoadingState.Loading
            )
        )
    }
}.catch { exception ->
    stateManager.emitSuspendAppUiEffect(
        uiEffect = AppUiEffects.ShowSnackBar(
            message = exception.localizedMessage ?: "An unexpected error occurred"
        )
    )
}