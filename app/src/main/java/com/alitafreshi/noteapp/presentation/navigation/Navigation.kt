package com.alitafreshi.noteapp.presentation.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.plusAssign
import com.alitafreshi.noteapp.presentation.intro.IntroScreen
import com.alitafreshi.noteapp.presentation.intro.NavGraphs
import com.alitafreshi.noteapp.presentation.intro.destinations.IntroScreenDestination
import com.alitafreshi.noteapp.presentation.navigation.util.scaleInEnterTransition
import com.alitafreshi.noteapp.presentation.navigation.util.scaleInPopEnterTransition
import com.alitafreshi.noteapp.presentation.navigation.util.scaleOutExitTransition
import com.alitafreshi.noteapp.presentation.navigation.util.scaleOutPopExitTransition
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
fun Navigation() {

    val navController = rememberAnimatedNavController()

    val bottomSheetNavigator = rememberBottomSheetNavigator()
    navController.navigatorProvider += bottomSheetNavigator

    ModalBottomSheetLayout(
        bottomSheetNavigator = bottomSheetNavigator,
        /* sheetShape = RoundedCornerShape(
             topStart = MaterialTheme.spacing.default,
             topEnd = MaterialTheme.spacing.default
         )*/
    ) {

        DestinationsNavHost(
            navController = navController,
            navGraph = NavGraphs.root,
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
                                /*TODO Navigate To Main Screen And Save The State To Data Store*/
                            })
                    })
            }
        )
    }
}