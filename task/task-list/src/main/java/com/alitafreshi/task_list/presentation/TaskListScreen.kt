package com.alitafreshi.task_list.presentation

import androidx.compose.animation.*
import androidx.compose.animation.core.*
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.alitafreshi.components.DefaultCentralizeTopBar
import com.alitafreshi.components.LogCompositions
import com.alitafreshi.components.util.spacing
import com.alitafreshi.domain.model.Note
import com.alitafreshi.resource.R
import kotlin.math.roundToInt

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
fun TaskListScreen(
    modifier: Modifier = Modifier,
    taskListViewState: TaskListViewState,
    taskListStateEvents: (TaskListEvents) -> Unit,
    topBarTitle: String = "Noto",
    taskBackGroundColor: Color,
    fabBackGroundColor: Color = MaterialTheme.colors.primary,
    descriptionTextStyle: TextStyle = MaterialTheme.typography.subtitle1,
    navigateToAddNewTask: (id: Int?) -> Unit
) {

    LogCompositions(msg = "TaskListScreen")

    val fabHeight by remember {
        mutableStateOf(72.dp)
    }

    val fabHeightPx = with(
        LocalDensity.current
    ) {
        fabHeight.roundToPx().toFloat()
    }

    val fabOffsetHeightPx = remember { mutableStateOf(0f) }

    val fabBackgroundColor by animateColorAsState(
        targetValue = if (taskListViewState.selectedTaskList.isEmpty()) fabBackGroundColor else MaterialTheme.colors.error,
        animationSpec = keyframes {
            durationMillis = 600
            fabBackGroundColor.at(300).with(easing = LinearEasing)
        }
    )

    val animatedRotateAngle by
    animateFloatAsState(
        targetValue = if (taskListViewState.selectedTaskList.isEmpty()) 0.0f else 135.0f,
        animationSpec = tween(durationMillis = 600)
    )



    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {

                val delta = available.y
                val newOffset = fabOffsetHeightPx.value + delta
                fabOffsetHeightPx.value = newOffset.coerceIn(-fabHeightPx, 0f)
                return Offset.Zero
            }
        }
    }


    BoxWithConstraints(modifier = modifier.fillMaxSize()) {
        Scaffold(
            modifier =
            Modifier
                .fillMaxSize()
                .nestedScroll(connection = nestedScrollConnection),
            topBar = {
                LogCompositions(msg = "TaskListTopBar")

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
                    selectedTaskList = taskListViewState.selectedTaskList,
                    taskBackGroundColor = taskBackGroundColor,
                    descriptionTextStyle = descriptionTextStyle,
                    navigateToAddNewTask = navigateToAddNewTask,
                    activeSelectionMode = {
                        taskListStateEvents(TaskListEvents.AddToSelectionList(note = it))
                    }
                )
            },
            floatingActionButton = {
                LogCompositions(msg = "floatingActionButton")
                if (taskListViewState.taskList.isNotEmpty())
                    FloatingActionButton(
                        onClick = {
                            if (taskListViewState.selectedTaskList.isNotEmpty())
                                taskListStateEvents(
                                    TaskListEvents.DeleteNotes(
                                        deletedNotes = taskListViewState.selectedTaskList
                                    )
                                )
                            else
                                navigateToAddNewTask(null)
                        },
                        shape = CircleShape,
                        backgroundColor = fabBackgroundColor,
                        modifier = Modifier
                            .offset {
                                IntOffset(
                                    //TODO CALCULATION OF X IS WRONG
                                    x = (((-((minWidth).roundToPx())).toDp() + 90.dp).roundToPx()) / 2,
                                    y = -fabOffsetHeightPx.value.roundToInt()

                                )
                            }
                    ) {
                        Icon(
                            modifier = Modifier.rotate(animatedRotateAngle),
                            imageVector = Icons.Filled.Add,
                            tint = Color.White,
                            contentDescription = "Add Items"
                        )
                    }
            }
        )
    }
}

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
private fun TaskListScreenContent(
    modifier: Modifier = Modifier,
    taskList: List<Note>,
    selectedTaskList: List<Note>,
    taskBackGroundColor: Color,
    descriptionTextStyle: TextStyle = MaterialTheme.typography.subtitle1,
    navigateToAddNewTask: (id: Int?) -> Unit,
    activeSelectionMode: (Note) -> Unit
) {
    LogCompositions(msg = "TaskListScreenContent")
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.surface)
    ) {
        TaskList(
            taskList = taskList,
            selectedTaskList = selectedTaskList,
            backgroundColor = taskBackGroundColor,
            descriptionTextStyle = descriptionTextStyle,
            navigateToAddNewTask = navigateToAddNewTask,
            activeSelectionMode = activeSelectionMode
        )
    }
}


//TODO CONTINUE FROM HERE AND CREATE CUSTOM ADD BUTTON
@Composable
fun AddNewTaskActionButton() {

}

