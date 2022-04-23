package com.alitafreshi.noteapp.presentation.app

import androidx.lifecycle.viewModelScope
import com.alitafreshi.data.datasource.local.datastore.AppProtoDataStore
import com.alitafreshi.data.datasource.local.datastore.AppSettings
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.tafreshiali.ayan_core.util.BottomSheetState
import ir.tafreshiali.ayan_core.util.DataState
import ir.tafreshiali.ayan_core.util.UIComponent
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val appProtoDataStoreImpl: AppProtoDataStore<AppSettings>
) : BaseViewModel<AppViewState, AppEvents, UIComponent>() {
    init {
        readIntroState()
    }

    override fun initNewViewState(): AppViewState = AppViewState()

    override fun onTriggerEvent(event: AppEvents) {
        when (event) {
            is AppEvents.UpdateIntroState -> {
                viewModelScope.launch {
                    updateIntroState(introState = event.introState)
                }
            }
        }
    }

    private fun readIntroState() {
        appProtoDataStoreImpl.getValue().onEach { dataState ->
            when (dataState) {
                is DataState.Loading -> {
                    setViewState(viewState = getCurrentViewStateOrNew().copy(loadingState = dataState.bottomSheetState))
                }
                is DataState.Data -> {
                    dataState.data?.introState?.let { introState ->
                        setViewState(
                            viewState = getCurrentViewStateOrNew().copy(
                                introState = introState
                            )
                        )
                    }
                }
                is DataState.Error -> {
                    //TODO Handling Some Exceptions That is happen during the data extraction
                }
            }
        }.launchIn(viewModelScope)
    }

    private suspend fun updateIntroState(introState: Boolean) {

        appProtoDataStoreImpl.getValue().onEach { dataState ->
            when (dataState) {
                is DataState.Loading -> {}
                is DataState.Data -> {
                    appProtoDataStoreImpl.setValue(
                        savedObj = dataState.data?.copy(introState = introState) ?: AppSettings(
                            introState = introState
                        )
                    )
                }
                is DataState.Error -> {
                    //TODO Handling Some Exceptions That is happen during the data extraction
                }
            }

        }.launchIn(viewModelScope)

        setViewState(
            viewState = getCurrentViewStateOrNew().copy(
                introState = introState
            )
        )
    }
}