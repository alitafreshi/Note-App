package com.alitafreshi.noteapp.presentation.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.plusAssign
import com.alitafreshi.components.util.spacing
import com.alitafreshi.noteapp.presentation.NavGraphs
import com.alitafreshi.components.util.app.AppEvents
import com.alitafreshi.components.util.app.AppViewState
import com.alitafreshi.noteapp.presentation.destinations.IntroScreenDestination
import com.alitafreshi.noteapp.presentation.destinations.TaskListDestinationDestination
import com.alitafreshi.noteapp.presentation.intro.IntroScreen
import com.alitafreshi.noteapp.presentation.navigation.destinations.task.task_list.TaskListDestination
import com.alitafreshi.noteapp.presentation.navigation.util.scaleInEnterTransition
import com.alitafreshi.noteapp.presentation.navigation.util.scaleInPopEnterTransition
import com.alitafreshi.noteapp.presentation.navigation.util.scaleOutExitTransition
import com.alitafreshi.noteapp.presentation.navigation.util.scaleOutPopExitTransition
import com.alitafreshi.noteapp.presentation.navigation.util.states.loading.HandelBottomSheetState
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.defaults.RootNavGraphDefaultAnimations
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import com.ramcosta.composedestinations.manualcomposablecalls.animatedComposable

@ExperimentalMaterialNavigationApi
@ExperimentalAnimationApi
@Composable
fun Navigation(
    appViewState: AppViewState,
    appEvents: (AppEvents) -> Unit
) {

    val navController = rememberAnimatedNavController()

    val bottomSheetNavigator = rememberBottomSheetNavigator()
    navController.navigatorProvider += bottomSheetNavigator
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination


    ModalBottomSheetLayout(
        bottomSheetNavigator = bottomSheetNavigator,
        sheetShape = RoundedCornerShape(
            topStart = MaterialTheme.spacing.default,
            topEnd = MaterialTheme.spacing.default
        )
    ) {

        DestinationsNavHost(
            navController = navController,
            navGraph = NavGraphs.root,
            startRoute = if (appViewState.introState) TaskListDestinationDestination else IntroScreenDestination,
            engine = rememberAnimatedNavHostEngine(
                rootDefaultAnimations = RootNavGraphDefaultAnimations(
                    enterTransition = { scaleInEnterTransition() },
                    exitTransition = { scaleOutExitTransition() },
                    popEnterTransition = { scaleInPopEnterTransition() },
                    popExitTransition = { scaleOutPopExitTransition() }
                )
            ),
            manualComposableCallsBuilder = {
                animatedComposable(
                    destination = IntroScreenDestination,
                    content = {
                        IntroScreen(
                            navigateToMainScreen = {
                                appEvents(AppEvents.UpdateIntroState(introState = true))
                                destinationsNavigator.navigate(
                                    TaskListDestinationDestination,
                                    onlyIfResumed = true
                                )
                            })
                    })

                animatedComposable(
                    destination = TaskListDestinationDestination,
                    content = {
                        TaskListDestination()
                    }
                )
            }
        )
    }

    HandelBottomSheetState(
        navController = navController,
        bottomSheetState = appViewState.loadingState,
        currentDestination = currentDestination
    )
}