package com.alitafreshi.task_list.presentation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import com.alitafreshi.components.util.spacing
import com.alitafreshi.domain.LoadingState
import com.alitafreshi.room_db.task.model.Note
import com.alitafreshi.task.components.TaskItem



@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
fun TaskList(
    modifier: Modifier = Modifier,
    loadingState: LoadingState,
    taskList: List<Note>,
    selectedTaskList: List<Int>,
    shape: Shape = MaterialTheme.shapes.medium,
    backgroundColor: Color = MaterialTheme.colors.surface,
    titleTextStyle: TextStyle = MaterialTheme.typography.subtitle1,
    descriptionTextStyle: TextStyle = MaterialTheme.typography.subtitle1,
    dateTextStyle: TextStyle = MaterialTheme.typography.caption,
    navigateToAddNewTask: (id: Int) -> Unit,
    activeSelectionMode: (Note) -> Unit
) {
    if (taskList.isEmpty() && loadingState == LoadingState.Idle) {

        EmptyTaskList(navigateToAddNewTask = { navigateToAddNewTask(-1) })
    } else {
        LazyVerticalStaggeredGrid(
            modifier = modifier.fillMaxSize(),
            columns = StaggeredGridCells.Fixed(count = 2),
            contentPadding = PaddingValues(MaterialTheme.spacing.default),
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
            verticalItemSpacing = MaterialTheme.spacing.small,
        ) {
            itemsIndexed(items = taskList, key = { _, item -> item.localId!! }) { _, note ->
                TaskItem(
                    shape = shape,
                    backgroundColor = backgroundColor,
                    noteTitle = note.title,
                    titleTextStyle = titleTextStyle,
                    noteDescription = note.description,
                    descriptionTextStyle = descriptionTextStyle,
                    noteDate = note.date,
                    dateTextStyle = dateTextStyle,
                    isInSelectionMode = selectedTaskList.isNotEmpty(),
                    isSelected = selectedTaskList.isNotEmpty() && selectedTaskList.contains(note.localId),
                    onItemClick = {
                        if (selectedTaskList.isEmpty())
                            navigateToAddNewTask(note.localId ?: -1)
                        else
                            activeSelectionMode(note)
                    },
                    activeSelectionMode = {
                        activeSelectionMode(note)
                    }
                )
            }
        }
    }
}





