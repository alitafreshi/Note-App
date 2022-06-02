package com.alitafreshi.task_list.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import com.alitafreshi.task.components.TaskItem
import com.alitafreshi.domain.model.Note


@Composable
fun TaskList(
    modifier: Modifier = Modifier,
    taskList: List<Note>,
    shape: Shape = MaterialTheme.shapes.medium,
    backgroundColor: Color = MaterialTheme.colors.surface,
    titleTextStyle: TextStyle = MaterialTheme.typography.subtitle1,
    descriptionTextStyle: TextStyle = MaterialTheme.typography.subtitle1,
    dateTextStyle: TextStyle = MaterialTheme.typography.caption,
    navigateToAddNewTask: () -> Unit
) {
    if (taskList.isEmpty()) {

        EmptyTaskList(navigateToAddNewTask = navigateToAddNewTask)
    } else {

        LazyColumn(modifier = modifier.fillMaxSize()) {
            itemsIndexed(items = taskList, key = { _, item ->
                item.id!!
            }) { index, note ->
                TaskItem(
                    shape = shape,
                    backgroundColor = backgroundColor,
                    noteTitle = note.title,
                    titleTextStyle = titleTextStyle,
                    noteDescription = note.description,
                    descriptionTextStyle = descriptionTextStyle,
                    noteDate = note.date,
                    dateTextStyle = dateTextStyle,
                    noteColor = note.color
                )
            }
        }
    }


}





