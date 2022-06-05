package com.alitafreshi.task_add_edit

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.alitafreshi.components.LogCompositions
import com.alitafreshi.components.util.spacing
import com.alitafreshi.resource.R
import com.alitafreshi.task.components.TransparentHintTextField
import com.alitafreshi.task_add_edit.view_event.AdEditEvents
import com.alitafreshi.task_add_edit.view_state.TaskAdEditTextFieldState

@Composable
fun TaskAdEditScreen(
    modifier: Modifier = Modifier,
//    taskAdEditTitleTextFieldState: TaskAdEditTextFieldState,
//    taskAdEditDescriptionTextFieldState: TaskAdEditTextFieldState,
    taskAdEditTitleText: String,
    taskAdEditTitleHintText: String,
    taskAdEditTitleIsHintVisible: Boolean,
    taskAdEditDescriptionText: String,
    taskAdEditDescriptionHintText: String,
    taskAdEditDescriptionIsHintVisible: Boolean,
    adEditEvents: (AdEditEvents) -> Unit,
    navigateBack: () -> Unit,
) {
    LogCompositions(msg = "TaskAdEditScreen")

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.default)
    ) {

        item {
            TitleTextFieldContainer(
                taskAdEditTitleText = taskAdEditTitleText,
                taskAdEditTitleHintText = taskAdEditTitleHintText,
                taskAdEditTitleIsHintVisible = taskAdEditTitleIsHintVisible,
                adEditEvents = adEditEvents,
                navigateBack = navigateBack
            )
        }

        item {
            DescriptionTextFieldContainer(
                taskAdEditDescriptionText = taskAdEditDescriptionText,
                taskAdEditDescriptionHintText = taskAdEditDescriptionHintText,
                taskAdEditDescriptionIsHintVisible = taskAdEditDescriptionIsHintVisible,
                adEditEvents = adEditEvents
            )
        }
    }
}

@Composable
fun TitleTextFieldContainer(
    modifier: Modifier = Modifier,
    taskAdEditTitleText: String,
    taskAdEditTitleHintText: String,
    taskAdEditTitleIsHintVisible: Boolean,
    adEditEvents: (AdEditEvents) -> Unit,
    navigateBack: () -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TransparentHintTextField(
            modifier = Modifier.weight(3f),
            text = taskAdEditTitleText,
            hint = taskAdEditTitleHintText,
            recompositionDebugTitle = "TaskAdEditTitle",
            isHintVisible = taskAdEditTitleIsHintVisible,
            singleLine = true,
            textStyle = MaterialTheme.typography.h5,
            onValueChange = { value ->
                adEditEvents(AdEditEvents.UpdateTitleContent(value = value))
            },
            onFocusChangeListener = { focusState ->
                adEditEvents(AdEditEvents.ChangeTitleFocusState(focusState = focusState))
            }
        )

        Icon(
            modifier = Modifier
                .weight(0.5f)
                .clickable(onClick = navigateBack),
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = "btn back"
        )
    }
}


@Composable
fun DescriptionTextFieldContainer(
    modifier: Modifier = Modifier,
    taskAdEditDescriptionText: String,
    taskAdEditDescriptionHintText: String,
    taskAdEditDescriptionIsHintVisible: Boolean,
    adEditEvents: (AdEditEvents) -> Unit,
) {
    TransparentHintTextField(
        modifier = modifier.fillMaxWidth(),
        text = taskAdEditDescriptionText,
        hint = taskAdEditDescriptionHintText,
        isHintVisible = taskAdEditDescriptionIsHintVisible,
        recompositionDebugTitle = "TaskAdEditDescription",
        textStyle = MaterialTheme.typography.body1,
        onValueChange = { value ->
            adEditEvents(AdEditEvents.UpdateDescriptionContent(value = value))
        },
        onFocusChangeListener = { focusState ->
            adEditEvents(AdEditEvents.ChangeDescriptionState(focusState = focusState))
        }
    )
}