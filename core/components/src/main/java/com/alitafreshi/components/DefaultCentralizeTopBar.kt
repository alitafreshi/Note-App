package com.alitafreshi.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.alitafreshi.components.util.spacing

@Composable
fun DefaultCentralizeTopBar(
    modifier: Modifier = Modifier,
    toolbarTitle: String,
    toolbarTitleTextStyle: TextStyle = MaterialTheme.typography.h6,
    backgroundColor: Color = MaterialTheme.colors.background,
    elevation: Dp = MaterialTheme.spacing.extraSmall,
    contentPadding: PaddingValues = PaddingValues(
        horizontal = MaterialTheme.spacing.default,
        vertical = MaterialTheme.spacing.extraSmall
    ),
    navigationIcon: @Composable (modifier: Modifier) -> Unit = {},
    actionIcon: @Composable (modifier: Modifier) -> Unit = {},
) {

    TopAppBar(
        backgroundColor = backgroundColor,
        contentPadding = contentPadding,
        elevation = elevation
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(MaterialTheme.spacing.topBar)
        ) {
            Text(
                text = toolbarTitle,
                style = toolbarTitleTextStyle,
                maxLines = 1,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center)
            )

            ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                val (actionView, navigationView) = createRefs()

                navigationIcon(modifier = Modifier.constrainAs(navigationView) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                })

                actionIcon(
                    modifier = Modifier.constrainAs(actionView) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                    })
            }
        }
    }
}