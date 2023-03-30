package com.alitafreshi.state_manager

import com.alitafreshi.data.qualifier.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class AppStateManager @Inject constructor(@ApplicationScope private val applicationScope: CoroutineScope) {

    private val _appEvents = MutableSharedFlow<AppEvents>()
    val appEvents = _appEvents.asSharedFlow()

     fun emitSuspendAppEvent(event: AppEvents) {
        applicationScope.launch {
            _appEvents.emit(value = event)
        }
    }

     fun emitAppEvent(event: AppEvents) {
        _appEvents.tryEmit(value = event)
    }


}