package com.alitafreshi.task_list.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.alitafreshi.components.DefaultCentralizeTopBar
import com.alitafreshi.components.util.spacing
import com.alitafreshi.task.components.floatingActionButtonState
import com.alitafreshi.domain.model.Note
import com.alitafreshi.resource.R
import kotlin.math.roundToInt

@ExperimentalFoundationApi
@Composable
fun TaskListScreen(
    modifier: Modifier = Modifier,
    taskListViewState: TaskListViewState,
    taskListStateEvents: (TaskListEvents) -> Unit,
    topBarTitle: String = "Noto",
    taskBackGroundColor: Color,
    descriptionTextStyle: TextStyle = MaterialTheme.typography.subtitle1,
    navigateToAddNewTask: (id: Int?) -> Unit
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
                DefaultCentralizeTopBar(
                    toolbarTitle = topBarTitle,
                    actionIcon = {
                        Image(
                            modifier = it.size(MaterialTheme.spacing.menuIconMedium),
                            painter = painterResource(id = R.drawable.ic_app),
                            contentDescription = "img app"
                        )
                    }
                )
            },
            content = {
                TaskListScreenContent(
                    taskList = taskListViewState.taskList,
                    taskBackGroundColor = taskBackGroundColor,
                    descriptionTextStyle=descriptionTextStyle,
                    navigateToAddNewTask = navigateToAddNewTask
                )
            },
            floatingActionButton = {
                if (taskListViewState.taskList.isNotEmpty()) {
                    FloatingActionButton(
                        onClick = { navigateToAddNewTask(null) },
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

@ExperimentalFoundationApi
@Composable
private fun TaskListScreenContent(
    modifier: Modifier = Modifier,
    taskList: List<Note>,
    taskBackGroundColor: Color,
    descriptionTextStyle: TextStyle = MaterialTheme.typography.subtitle1,
    navigateToAddNewTask: (id: Int?) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.surface)
    ) {
        TaskList(
            taskList = taskList,
            backgroundColor = taskBackGroundColor,
            descriptionTextStyle=descriptionTextStyle,
            navigateToAddNewTask = navigateToAddNewTask
        )
    }
}


//TODO CONTINUE FROM HERE AND CREATE CUSTOM ADD BUTTON
@Composable
fun AddNewTaskActionButton() {

}

