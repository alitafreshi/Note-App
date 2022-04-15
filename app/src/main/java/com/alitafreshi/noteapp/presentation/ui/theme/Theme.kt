package com.alitafreshi.noteapp.presentation.ui.theme

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.LocalOverScrollConfiguration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import com.alitafreshi.components.util.Spacing
import com.alitafreshi.components.util.localSpacing

private val DarkColorPalette = darkColors(
    primary = primaryLightColor,
    primaryVariant = primaryDarkColor,
    error = errorColor,
    onError = Color.White
)

private val LightColorPalette = lightColors(
    primary = primaryColor,
    primaryVariant = primaryDarkColor,
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    error = errorColor,
    onError = Color.White
)

@ExperimentalFoundationApi
@Composable
fun NoteAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {

    /*val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }*/

    CompositionLocalProvider(
        LocalLayoutDirection provides LayoutDirection.Rtl,
        LocalOverScrollConfiguration provides null,
        localSpacing provides Spacing()
    ) {
        MaterialTheme(
            colors = LightColorPalette,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}