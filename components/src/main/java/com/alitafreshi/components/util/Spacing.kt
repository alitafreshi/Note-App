package com.alitafreshi.components.util

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


/**
 * Here we define custom [MaterialTheme] property and pass it down to the all ( hierarchies / nodes ) */
data class Spacing(
    val unspecified: Dp = 0.dp,
    val default: Dp = 15.dp,
    val iconSmall: Dp = 18.dp,
    val iconMedium: Dp = 24.dp,
    val menuIconMedium: Dp = 30.dp,
    val extraSmall: Dp = 5.dp,
    val small: Dp = 10.dp,
    val medium: Dp = 15.dp,
    val large: Dp = 20.dp,
    val extraLarge: Dp = 25.dp,
    val buttonInnerPaddingSmall: Dp = 2.dp
)

val localSpacing = compositionLocalOf { Spacing() }
val MaterialTheme.spacing: Spacing
    @Composable
    @ReadOnlyComposable
    get() = localSpacing.current


