package com.alitafreshi.noteapp.presentation.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.alitafreshi.components.util.app.AppEvents
import com.alitafreshi.components.util.app.AppViewState
import com.alitafreshi.noteapp.presentation.navigation.Navigation
import com.alitafreshi.noteapp.presentation.ui.theme.NoteAppTheme
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import dagger.hilt.android.AndroidEntryPoint
import ir.tafreshiali.ayan_core.util.BottomSheetState

@ExperimentalFoundationApi
@ExperimentalMaterialNavigationApi
@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val appViewModel: AppViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            App(
                appViewState = appViewModel.getCurrentViewStateOrNew(),
                appEvents = appViewModel::onTriggerEvent
            )
        }
    }
}

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@ExperimentalMaterialNavigationApi
@Composable
fun App(
    appViewState: AppViewState,
    appEvents: (AppEvents) -> Unit
) {
    NoteAppTheme {
        Navigation(
            appViewState = appViewState,
            appEvents = appEvents
        )
    }
}
