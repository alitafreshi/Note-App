package com.alitafreshi.state_manager

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

class AppStateManager <T> {

    private val _appViewState = mutableStateOf<GenericState<T>>(GenericState())
    val appViewState: State<GenericState<T>> = _appViewState

}