package com.alitafreshi.noteapp.presentation.app

import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import com.alitafreshi.components.util.app.AppEvents
import com.alitafreshi.components.util.app.AppViewState
import com.alitafreshi.components.util.app.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.tafreshiali.ayan_core.util.BottomSheetState
import ir.tafreshiali.ayan_core.util.UIComponent
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : BaseViewModel<AppViewState, AppEvents, UIComponent>() {

    init {
        readIntroState(introState = sharedPreferences.getBoolean("IntroKey", false))
    }

    override fun initNewViewState(): AppViewState = AppViewState()

    override fun onTriggerEvent(event: AppEvents) {
        when (event) {

            is AppEvents.UpdateIntroState -> {
                updateIntroState(introState = event.introState)
            }

            is AppEvents.UpdateLoadingState -> {
                Log.d("AppEvents", "AppViewModel = ${event.bottomSheetState}")
                handleSuspendEvent {
                    delay(1000L)
                    setViewState(viewState = getCurrentViewStateOrNew().copy(loadingState = event.bottomSheetState))
                }
            }
        }

    }



    private fun readIntroState(introState: Boolean) {

        setViewState(
            viewState = getCurrentViewStateOrNew().copy(
                //loadingState = if (!introState)BottomSheetState.Loading else BottomSheetState.Idle,
                introState = introState
            )
        )
    }


    private fun updateIntroState(introState: Boolean) {

        sharedPreferences.edit {
            putBoolean("IntroKey", introState).apply()
        }
        setViewState(
            viewState = getCurrentViewStateOrNew().copy(
                introState = introState
            )
        )
    }
}