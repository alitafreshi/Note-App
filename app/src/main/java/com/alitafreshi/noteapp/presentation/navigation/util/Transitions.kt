package com.alitafreshi.noteapp.presentation.navigation.util


import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween

/** [slideOutHorizontally] Animation + [fadeOut] Animation*/

@ExperimentalAnimationApi
fun slideOutHorizontally(width: Int) = slideOutHorizontally(
    targetOffsetX = { width },
    animationSpec = tween(
        durationMillis = 300,
        easing = FastOutSlowInEasing
    )
) + fadeOut(animationSpec = tween(300))


/** [slideInHorizontally] Animation + [fadeIn] Animation*/
@ExperimentalAnimationApi
fun slideInHorizontally(width: Int) = slideInHorizontally(
    initialOffsetX = { width },
    animationSpec = tween(
        durationMillis = 300,
        easing = FastOutSlowInEasing
    )
) + fadeIn(animationSpec = tween(300))


/** Enter transition when you navigate to a screen*/

@ExperimentalAnimationApi
fun scaleInEnterTransition() = scaleIn(
    initialScale = .9f,
    animationSpec = tween(300)
) + fadeIn(
    animationSpec = tween(300)
)

/** Exit transition when you navigate to a screen*/
@ExperimentalAnimationApi
fun scaleOutExitTransition() = scaleOut(
    targetScale = 1.1f,
    animationSpec = tween(300)
) + fadeOut(
    animationSpec = tween(300)
)

/**Enter transition of a screen when you pop to it*/
@ExperimentalAnimationApi
fun scaleInPopEnterTransition() = scaleIn(
    initialScale = 1.1f,
    animationSpec = tween(300)
) + fadeIn(
    animationSpec = tween(300)
)

/**Exit transition of a screen you are popping from*/
@ExperimentalAnimationApi
fun scaleOutPopExitTransition() = scaleOut(
    targetScale = .9f,
    animationSpec = tween(300)
) + fadeOut(
    animationSpec = tween(300)
)
