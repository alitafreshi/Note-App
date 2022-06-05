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
    taskAdEditTitleTextFieldState: TaskAdEditTextFieldState,
    taskAdEditDescriptionTextFieldState: TaskAdEditTextFieldState,
    adEditEvents: (AdEditEvents) -> Unit,
    navigateBack: () -> Unit,
) {
    LogCompositions(msg = "TaskAdEditScreen")

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.default)
    ) {

        item {

            TransparentHintTextField(
                modifier = Modifier.fillMaxWidth(),
                text = taskAdEditTitleTextFieldState.text,
                hint = taskAdEditTitleTextFieldState.hint,
                recompositionDebugTitle = "TaskAdEditTitle",
                isHintVisible = taskAdEditTitleTextFieldState.isHintEnabled,
                singleLine = true,
                textStyle = MaterialTheme.typography.h5,
                onValueChange = { value ->
                    adEditEvents(AdEditEvents.UpdateTitleContent(value = value))
                },
                onFocusChangeListener = { focusState ->
                    adEditEvents(AdEditEvents.ChangeTitleFocusState(focusState = focusState))
                }
            )
        }

        item {
            TransparentHintTextField(
                modifier = modifier.fillMaxWidth(),
                text = taskAdEditDescriptionTextFieldState.text,
                hint = taskAdEditDescriptionTextFieldState.hint,
                isHintVisible = taskAdEditDescriptionTextFieldState.isHintEnabled,
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

        TransparentHintTextField(
            modifier = Modifier.fillMaxWidth(),
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