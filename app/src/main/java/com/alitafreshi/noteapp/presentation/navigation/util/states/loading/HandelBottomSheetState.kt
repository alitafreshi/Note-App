package com.alitafreshi.noteapp.presentation.navigation.util.states.loading

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavOptionsBuilder
import com.alitafreshi.noteapp.presentation.destinations.LoadingAnimationDestination
import com.ramcosta.composedestinations.navigation.navigate
import ir.tafreshiali.ayan_core.util.BottomSheetState

@Composable
fun HandelBottomSheetState(
    navController: NavController,
    bottomSheetState: BottomSheetState,
    currentDestination: NavDestination?
) {
    LaunchedEffect(key1 = bottomSheetState) {
        when (bottomSheetState) {
            is BottomSheetState.Loading -> {
                navController.navigate(
                    LoadingAnimationDestination(),
                    fun NavOptionsBuilder.() {
                        launchSingleTop = true
                    })
            }

            is BottomSheetState.Idle -> {
                currentDestination?.let {
                    if (it.route == LoadingAnimationDestination.route) {
                        navController.popBackStack()
                    }
                }
            }
            else -> {}
        }
    }
}