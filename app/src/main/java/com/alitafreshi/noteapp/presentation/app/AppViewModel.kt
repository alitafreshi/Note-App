package com.alitafreshi.noteapp.presentation.app

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.alitafreshi.components.util.app.AppEvents
import com.alitafreshi.components.util.app.AppViewState
import com.alitafreshi.components.util.app.BaseViewModel
import com.alitafreshi.components.util.app.Navigation
import com.alitafreshi.data.datasource.local.datastore.AppProtoDataStore
import com.alitafreshi.data.datasource.local.datastore.AppSettings
import com.alitafreshi.domain.DataState
import com.alitafreshi.state_manager.AppStateManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val applicationStateManager: AppStateManager,
    private val appProtoDataStore: AppProtoDataStore<AppSettings>
) : BaseViewModel<AppViewState, AppEvents, Unit>() {

    fun init() {
        readIntroState()
    }

    override fun initNewViewState(): AppViewState = AppViewState()

    override fun onTriggerEvent(event: AppEvents) {
        when (event) {

            is AppEvents.UpdateIntroState -> {
                handleSuspendEvent {
                    updateIntroState(introState = event.introState)
                }
            }

            is AppEvents.UpdateLoadingState -> {
                Log.d("AppEvents", "AppViewModel = ${event.loadingState}")
                handleSuspendEvent {
                    delay(1000L)
                    setViewState(viewState = getCurrentViewStateOrNew().copy(loadingState = event.loadingState))
                }
            }
        }

    }

    private fun readIntroState() {
        appProtoDataStore.getValue().onEach { dataState ->
            when (dataState) {
                is DataState.Data -> dataState.data?.let { appSettings ->
                    setViewState(
                        viewState = getCurrentViewStateOrNew().copy(
                            introState = appSettings.introState
                        )
                    )

                    applicationStateManager.emitSuspendAppUiEffect(uiEffect = Navigation.DetectStartGraph)
                }

                is DataState.Error -> TODO()

                is DataState.Loading -> setViewState(
                    viewState = getCurrentViewStateOrNew().copy(
                        loadingState = dataState.loadingState
                    )
                )
            }

        }.launchIn(viewModelScope)

    }

    private suspend fun updateIntroState(introState: Boolean) {
        appProtoDataStore.setValue(savedObj = AppSettings(introState = introState))
        setViewState(
            viewState = getCurrentViewStateOrNew().copy(
                introState = introState
            )
        )
    }
}