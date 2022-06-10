package com.alitafreshi.noteapp.presentation.navigation.destinations.task.task_list

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import com.alitafreshi.noteapp.presentation.destinations.TaskAdEditDestinationDestination
import com.alitafreshi.noteapp.presentation.ui.theme.taskBackGroundColor
import com.alitafreshi.noteapp.presentation.ui.theme.taskDescriptionColor
import com.alitafreshi.task_list.presentation.TaskListScreen
import com.alitafreshi.task_list.presentation.TaskListViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@RootNavGraph
@Destination
@Composable
fun TaskListDestination(
    navigator: DestinationsNavigator
) {
    val taskListViewState: TaskListViewModel = hiltViewModel()
    TaskListScreen(
        taskListViewState = taskListViewState.getCurrentViewStateOrNew(),
        taskListStateEvents = taskListViewState::onTriggerEvent,
        taskBackGroundColor = taskBackGroundColor,
        descriptionTextStyle = MaterialTheme.typography.subtitle1.copy(
            color = taskDescriptionColor
        ),
        navigateToAddNewTask = { noteId ->
            navigator.navigate(
                direction = TaskAdEditDestinationDestination(taskId = noteId),
                onlyIfResumed = true
            )
        }
    )
}