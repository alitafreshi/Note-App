package com.alitafreshi.task_list.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.alitafreshi.components.DefaultCentralizeTopBar
import com.alitafreshi.task.components.floatingActionButtonState
import com.alitafreshi.domain.model.Note
import kotlin.math.roundToInt

@Composable
fun TaskListScreen(
    modifier: Modifier = Modifier,
    taskListViewState: TaskListViewState,
    taskListStateEvents: (TaskListEvents) -> Unit,
    topBarTitle: String = "Note",
    navigateToAddNewTask: () -> Unit
) {
    var fabOffsetHeightPx by rememberSaveable { mutableStateOf(0f) }



    BoxWithConstraints(modifier = modifier.fillMaxSize()) {
        Scaffold(
            modifier =
            modifier
                .fillMaxSize()
                .nestedScroll(
                    connection = floatingActionButtonState(
                        fabOffsetHeightPx = fabOffsetHeightPx,
                        fabYAxisOffsetPx = {
                            fabOffsetHeightPx = it
                        })
                ),
            topBar = {
                DefaultCentralizeTopBar(toolbarTitle = topBarTitle)
            },
            content = {
                TaskListScreenContent(
                    taskList = taskListViewState.taskList,
                    navigateToAddNewTask = navigateToAddNewTask
                )
            },
            floatingActionButton = {
                if (taskListViewState.taskList.isNotEmpty()) {
                    FloatingActionButton(
                        onClick = navigateToAddNewTask,
                        shape = CircleShape,
                        backgroundColor = MaterialTheme.colors.primary,
                        modifier = Modifier
                            .offset {
                                IntOffset(
                                    //TODO CALCULATION OF X IS WRONG
                                    x = (((-((minWidth).roundToPx())).toDp() + 90.dp).roundToPx()) / 2,
                                    y = -fabOffsetHeightPx.roundToInt()

                                )
                            }
                    ) {
                        Icon(
                            Icons.Filled.Add,
                            tint = Color.White,
                            contentDescription = "Add Items"
                        )
                    }
                }
            }
        )


    }


}

@Composable
private fun TaskListScreenContent(
    modifier: Modifier = Modifier,
    taskList: List<Note>,
    navigateToAddNewTask: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.surface)
    ) {
        TaskList(taskList = taskList, navigateToAddNewTask = navigateToAddNewTask)
    }
}


//TODO CONTINUE FROM HERE AND CREATE CUSTOM ADD BUTTON
@Composable
fun AddNewTaskActionButton() {

}

