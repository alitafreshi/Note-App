package com.alitafreshi.noteapp.presentation.navigation.destinations.task.task_list

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.alitafreshi.task_list.presentation.TaskListScreen
import com.alitafreshi.task_list.presentation.TaskListViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph

@RootNavGraph
@Destination
@Composable
fun TaskListDestination() {
    val taskListViewState: TaskListViewModel = hiltViewModel()
    TaskListScreen(
        taskListViewState = taskListViewState.getCurrentViewStateOrNew(),
        taskListStateEvents = taskListViewState::onTriggerEvent,
        navigateToAddNewTask = {}
    )
}