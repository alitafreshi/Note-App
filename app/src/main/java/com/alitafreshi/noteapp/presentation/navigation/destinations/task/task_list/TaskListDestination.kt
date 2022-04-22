package com.alitafreshi.noteapp.presentation.navigation.destinations.task.task_list

import androidx.compose.runtime.Composable
import com.alitafreshi.task_list.ui.TaskList
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun TaskListDestination() {
    TaskList(taskList = emptyList()) {

    }
}