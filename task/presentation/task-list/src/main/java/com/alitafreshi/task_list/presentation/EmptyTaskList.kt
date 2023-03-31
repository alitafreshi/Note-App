package com.alitafreshi.task_list.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.alitafreshi.components.util.spacing
import com.alitafreshi.resource.R

@Composable
fun EmptyTaskList(
    modifier: Modifier = Modifier,
    navigateToAddNewTask: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = MaterialTheme.spacing.default),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f),
            painter = painterResource(id = R.drawable.ic_empty_note),
            contentDescription = "img empty task list"
        )

        Text(
            modifier = modifier.padding(vertical = MaterialTheme.spacing.default),
            text = stringResource(id = R.string.tv_empty_task_list),
            style = MaterialTheme.typography.h6.copy(
                color = Color.Black,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center
            )
        )


        Button(
            modifier = modifier.fillMaxWidth(0.5f),
            onClick = navigateToAddNewTask,
            shape = MaterialTheme.shapes.large
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.ic_add_new_tasksvg),
                    contentDescription = "btn add new task"
                )

                Text(
                    modifier = modifier.padding(start = MaterialTheme.spacing.small),
                    text = stringResource(id = R.string.btn_create_new_task),
                    style = MaterialTheme.typography.button.copy(color = Color.Black)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EmptyTaskListPreview() {
    EmptyTaskList(navigateToAddNewTask = {})
}