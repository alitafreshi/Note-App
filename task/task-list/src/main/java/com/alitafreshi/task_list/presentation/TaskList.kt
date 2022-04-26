package com.alitafreshi.task_list.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

// TODO should create task object
@Composable
fun TaskList(
    modifier: Modifier = Modifier,
    taskList: List<String>,
    navigateToAddNewTask: () -> Unit
) {
    if (taskList.isEmpty())
        EmptyTaskList(navigateToAddNewTask = navigateToAddNewTask)
}

