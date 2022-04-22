package com.alitafreshi.noteapp.presentation.app

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
 * @param [ViewState] [Events] [UiComponent] these are generic unique types for each screen
 * also all the related states for the whole app handles here (like canceling requests / retrying requests etc) */

abstract class BaseViewModel<ViewState, Events, UiComponent> :
    ViewModel() {

    private val _viewState: MutableState<ViewState> = mutableStateOf(this.initNewViewState())

    private val _events = MutableSharedFlow<AppEvents>()
    private val events = _events.asSharedFlow()

    fun getCurrentViewStateOrNew(): ViewState = _viewState.value ?: initNewViewState()

    fun setViewState(viewState: ViewState) {
        _viewState.value = viewState
    }

    fun observeAppEvents(): SharedFlow<AppEvents> = events

    fun emitAppEvent(event: AppEvents) {
        viewModelScope.launch {
            _events.emit(value = event)
        }
    }

    abstract fun onTriggerEvent(event: Events)

    abstract fun initNewViewState(): ViewState
}
