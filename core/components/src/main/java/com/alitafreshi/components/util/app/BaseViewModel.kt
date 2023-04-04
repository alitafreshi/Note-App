package com.alitafreshi.components.util.app

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

/**
 * GOAL = TO HAVE GENERIC PATTERN THAT CONTAINS ALL SCENARIOS
 * @param [ViewState] [Events] [UiEffects] these are generic unique types for each screen
 * also all the related states for the whole app handles here (like canceling requests / retrying requests etc) */

abstract class BaseViewModel<ViewState, Events, UiEffects> :
    ViewModel() {

    private val _viewState: MutableState<ViewState> = mutableStateOf(this.initNewViewState())

    private val _uiEffects = MutableSharedFlow<UiEffects>()
    private val uiEffects = _uiEffects.asSharedFlow()

    fun getCurrentViewStateOrNew(): ViewState = _viewState.value ?: initNewViewState()

    fun setViewState(viewState: ViewState) {
        _viewState.value = viewState
    }

    fun observeEvents(): SharedFlow<UiEffects> = uiEffects

    fun emitSuspendEvent(event: UiEffects) {
        viewModelScope.launch {
            _uiEffects.emit(value = event)
        }
    }

    fun handleSuspendEvent(suspendBlock: (suspend () -> Unit)) {
        viewModelScope.launch {
            suspendBlock()
        }
    }

    abstract fun onTriggerEvent(event: Events)

    abstract fun initNewViewState(): ViewState
}
