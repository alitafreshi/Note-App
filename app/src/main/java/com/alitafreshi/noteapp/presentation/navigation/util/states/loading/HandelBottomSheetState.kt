package com.alitafreshi.noteapp.presentation.navigation.util.states.loading

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import com.alitafreshi.noteapp.presentation.destinations.LoadingAnimationDestination
import com.ramcosta.composedestinations.navigation.navigateTo
import ir.tafreshiali.ayan_core.util.BottomSheetState
import kotlinx.coroutines.delay

@Composable
fun HandelBottomSheetState(
    navController: NavController,
    bottomSheetState: BottomSheetState,
    currentDestination: NavDestination?
) {
    LaunchedEffect(key1 = bottomSheetState) {
        when (bottomSheetState) {
            is BottomSheetState.Loading -> {
                navController.navigateTo(
                    LoadingAnimationDestination()
                ) {
                    launchSingleTop = true
                }
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