package com.alitafreshi.task.components

import androidx.compose.animation.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import com.alitafreshi.components.util.spacing


@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
fun TaskItem(
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.shapes.medium,
    backgroundColor: Color = MaterialTheme.colors.surface,
    noteTitle: String,
    titleTextStyle: TextStyle = MaterialTheme.typography.subtitle1,
    noteDescription: String,
    descriptionTextStyle: TextStyle = MaterialTheme.typography.subtitle1,
    noteDate: String,
    dateTextStyle: TextStyle = MaterialTheme.typography.caption,
    isInSelectionMode: Boolean = false,
    isSelected: Boolean = false,
    onItemClick: () -> Unit,
    activeSelectionMode: () -> Unit
) {

    Card(
        modifier = modifier.combinedClickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() },
            onClick = onItemClick,
            onLongClick = activeSelectionMode
        ),
        shape = shape,
        backgroundColor = backgroundColor
    ) {
        Column(
            modifier = Modifier.padding(MaterialTheme.spacing.small),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.default)
        ) {

            Row(
                modifier = modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {

                AnimatedVisibility(
                    visible = isInSelectionMode,
                    enter = fadeIn() + expandHorizontally() + scaleIn(),
                    exit = fadeOut() + shrinkHorizontally()+ scaleOut()
                ) {
                    CustomRadioButton(
                        selected = isSelected,
                        onClick = onItemClick,
                        colors = RadioButtonDefaults.colors(selectedColor = MaterialTheme.colors.primaryVariant)
                    )
                }

                Text(text = noteTitle, style = titleTextStyle)
            }


            Text(text = noteDescription, style = descriptionTextStyle)

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = noteDate, style = dateTextStyle.copy(
                        color = MaterialTheme.colors.onBackground.copy(
                            alpha = 0.5f
                        )
                    )
                )

                Box(
                    modifier = Modifier
                        .background(
                            shape = CircleShape,
                            //TODO MUST BE DONE

                            // color = colorResource(id = noteColor)

                            color = MaterialTheme.colors.primary
                        )
                        .size(MaterialTheme.spacing.small)
                )
            }
        }
    }
}