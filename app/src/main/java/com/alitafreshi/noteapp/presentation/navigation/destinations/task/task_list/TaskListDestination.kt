package com.alitafreshi.noteapp.presentation.navigation.destinations.task.task_list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.alitafreshi.noteapp.presentation.destinations.TaskAdEditDestinationDestination
import com.alitafreshi.noteapp.presentation.ui.theme.taskBackGroundColor
import com.alitafreshi.task_list.presentation.TaskListScreen
import com.alitafreshi.task_list.presentation.TaskListViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

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
        navigateToAddNewTask = { noteId ->
            navigator.navigate(
                direction = TaskAdEditDestinationDestination(taskId = noteId),
                onlyIfResumed = true
            )
        }
    )
}