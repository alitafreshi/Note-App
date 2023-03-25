package com.alitafreshi.noteapp.presentation.loading

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.alitafreshi.components.util.spacing
import kotlinx.coroutines.delay


@Composable
fun LoadingAnimation(
    modifier: Modifier = Modifier,
    circleSize: Dp = MaterialTheme.spacing.extraLarge,
    circleColor: Color = MaterialTheme.colors.primary,
    spaceBetween: Dp = MaterialTheme.spacing.small,
    travelDistance: Dp = MaterialTheme.spacing.large
) {


    val circles = listOf(
        remember { Animatable(initialValue = 0f) },
        remember { Animatable(initialValue = 0f) },
        remember { Animatable(initialValue = 0f) }
    )

    circles.forEachIndexed { index, animatable ->
        HandleAnimationState(animatable = animatable, itemIndex = index)
    }

    val circleValues = circles.map { it.value }
    val distance = with(LocalDensity.current) { travelDistance.toPx() }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(spaceBetween)
        ) {
            circleValues.forEachIndexed  { _, value ->
                CircleLoadingView(animatableValue = value, distance = distance)
            }
        }
    }
}

@Composable
fun CircleLoadingView(
    modifier: Modifier = Modifier,
    circleSize: Dp = MaterialTheme.spacing.extraLarge,
    circleColor: Color = MaterialTheme.colors.primary,
    animatableValue: Float,
    distance: Float
) {

    Box(
        modifier = modifier
            .size(circleSize)
            .graphicsLayer {
                translationY = -animatableValue * distance
            }
            .background(color = circleColor, shape = CircleShape)
    )

}


@Composable
fun HandleAnimationState(
    animatable: Animatable<Float, AnimationVector1D>,
    itemIndex: Int,
    animationDuration: Int = 1200,
    delayDurationPerEachCircle: Long = 100L
) {
    LaunchedEffect(key1 = animatable) {
        delay(itemIndex * delayDurationPerEachCircle)
        animatable.animateTo(
            targetValue = 1f, animationSpec = infiniteRepeatable(
                animation = keyframes {
                    durationMillis = animationDuration
                    0.0f at 0 with LinearOutSlowInEasing
                    1.0f at 300 with LinearOutSlowInEasing
                    0.0f at 600 with LinearOutSlowInEasing
                    0.0f at 1200 with LinearOutSlowInEasing
                },
                repeatMode = RepeatMode.Restart
            )
        )
    }
}


@Preview(showBackground = true)
@Composable
fun LoadingAnimationPreview() {
    LoadingAnimation()
}

