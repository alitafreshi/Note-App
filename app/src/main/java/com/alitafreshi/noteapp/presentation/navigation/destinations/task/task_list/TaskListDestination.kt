package com.alitafreshi.noteapp.presentation.navigation.destinations.task.task_list

import androidx.compose.runtime.Composable
import com.alitafreshi.task_list.presentation.TaskList
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph

@RootNavGraph
@Destination
@Composable
fun TaskListDestination() {
    TaskList(taskList = emptyList()) {

    }
}