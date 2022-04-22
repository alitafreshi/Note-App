package com.alitafreshi.noteapp.presentation.app

import androidx.lifecycle.viewModelScope
import com.alitafreshi.data.datasource.local.datastore.AppProtoDataStore
import com.alitafreshi.data.datasource.local.datastore.AppSettings
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.tafreshiali.ayan_core.util.UIComponent
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val appProtoDataStoreImpl: AppProtoDataStore<AppSettings>
) : BaseViewModel<AppViewState, AppEvents, UIComponent>() {
    init {
        //TODO Read Value From Data Store And Update The App View State
        viewModelScope.launch {
            readIntroState()
        }
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

    private suspend fun readIntroState() {
        appProtoDataStoreImpl.getValue().first().savedObj?.let {
            setViewState(
                viewState = getCurrentViewStateOrNew().copy(
                    introState = it.introState
                )
            )
        }
    }

    private suspend fun updateIntroState(introState: Boolean) {
        appProtoDataStoreImpl.setValue(
            savedObj = appProtoDataStoreImpl.getValue()
                .first().savedObj?.copy(introState = introState)
                ?: AppSettings(introState = introState)
        )
        setViewState(
            viewState = getCurrentViewStateOrNew().copy(
                introState = introState
            )
        )
    }
}