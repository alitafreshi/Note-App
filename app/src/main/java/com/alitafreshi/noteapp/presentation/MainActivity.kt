package com.alitafreshi.noteapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.alitafreshi.noteapp.presentation.navigation.Navigation
import com.alitafreshi.noteapp.presentation.ui.theme.NoteAppTheme
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi

@ExperimentalFoundationApi
@ExperimentalMaterialNavigationApi
@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            App()
        }
    }
}

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@ExperimentalMaterialNavigationApi
@Composable
fun App() {
    NoteAppTheme {
        Navigation()
    }
}
