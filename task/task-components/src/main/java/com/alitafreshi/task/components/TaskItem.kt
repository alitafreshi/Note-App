package com.alitafreshi.task.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import com.alitafreshi.components.util.spacing
import com.alitafreshi.domain.model.Note


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
    noteColor: Int
) {

    Card(
        modifier = modifier.background(color = backgroundColor),
        shape = shape
    ) {
        Column(
            modifier = Modifier.padding(MaterialTheme.spacing.small),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.default)
        ) {

            Text(text = noteTitle, style = titleTextStyle)

            Text(text = noteDescription, style = descriptionTextStyle)

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(text = noteDate, style = dateTextStyle)

                Box(
                    modifier = Modifier
                        .background(
                            shape = CircleShape,
                            color = colorResource(id = noteColor)
                        )
                        .size(MaterialTheme.spacing.small)
                )
            }
        }
    }
}