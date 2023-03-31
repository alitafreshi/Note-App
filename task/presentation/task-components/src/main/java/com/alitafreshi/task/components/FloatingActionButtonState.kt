package com.alitafreshi.task.components

import androidx.compose.runtime.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@Composable
fun floatingActionButtonState(
    fabOffsetHeightPx: Float,
    fabYAxisOffsetPx: (Float) -> Unit
): NestedScrollConnection {

    val fabHeight by remember {
        mutableStateOf(72.dp)
    }

    val fabHeightPx = with(
        LocalDensity.current
    ) {
        fabHeight.roundToPx().toFloat()
    }

    //var fabOffsetHeightPx by rememberSaveable { mutableStateOf(0f) }

    return remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {

                val delta = available.y
                val newOffset = fabOffsetHeightPx + delta
                //fabOffsetHeightPx = newOffset.coerceIn(-fabHeightPx, 0f)
                fabYAxisOffsetPx(newOffset.coerceIn(-fabHeightPx, 0f))
                return Offset.Zero
            }
        }
    }
}
