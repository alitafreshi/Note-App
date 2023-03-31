package com.alitafreshi.task_add_edit

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
import com.alitafreshi.components.util.noRippleClickable
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

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.default)
    ) {

        item {
            TitleTextFieldContainer(
                taskAdEditTitleText = taskAdEditTitleTextFieldState.text,
                taskAdEditTitleHintText = taskAdEditTitleTextFieldState.hint,
                taskAdEditTitleIsHintVisible = taskAdEditTitleTextFieldState.isHintEnabled,
                adEditEvents = adEditEvents,
                navigateBack = navigateBack
            )
        }

        item {
            DescriptionTextFieldContainer(
                taskAdEditDescriptionText = taskAdEditDescriptionTextFieldState.text,
                taskAdEditDescriptionHintText = taskAdEditDescriptionTextFieldState.hint,
                taskAdEditDescriptionIsHintVisible = taskAdEditDescriptionTextFieldState.isHintEnabled,
                adEditEvents = adEditEvents
            )
        }
    }
}

@Composable
fun TitleTextFieldContainer(
    taskAdEditTitleText: String,
    taskAdEditTitleHintText: String,
    taskAdEditTitleIsHintVisible: Boolean,
    adEditEvents: (AdEditEvents) -> Unit,
    navigateBack: () -> Unit,
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        TransparentHintTextField(
            modifier = Modifier.weight(1f),
            text = taskAdEditTitleText,
            hint = taskAdEditTitleHintText,
            isHintVisible = taskAdEditTitleIsHintVisible,
            singleLine = true,
            textStyle = MaterialTheme.typography.h5,
            hintTextStyle =MaterialTheme.typography.h5.copy(
                color = MaterialTheme.colors.onBackground.copy(
                    alpha = 0.6f
                )
            ) ,
            onValueChange = { value ->
                adEditEvents(AdEditEvents.UpdateTitleContent(value = value))
            },
            onFocusChangeListener = { focusState ->
                adEditEvents(AdEditEvents.ChangeTitleFocusState(focusState = focusState))
            }
        )

        Icon(
            modifier = Modifier
                .weight(0.1f)
                .noRippleClickable(onClick = navigateBack),
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = "btn back"
        )
    }
}


@Composable
fun DescriptionTextFieldContainer(
    taskAdEditDescriptionText: String,
    taskAdEditDescriptionHintText: String,
    taskAdEditDescriptionIsHintVisible: Boolean,
    adEditEvents: (AdEditEvents) -> Unit,
) {
    TransparentHintTextField(
        modifier = Modifier.fillMaxWidth(),
        text = taskAdEditDescriptionText,
        hint = taskAdEditDescriptionHintText,
        isHintVisible = taskAdEditDescriptionIsHintVisible,
        textStyle = MaterialTheme.typography.body1,
        hintTextStyle = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.onBackground.copy(alpha = 0.4f)),
        onValueChange = { value ->
            adEditEvents(AdEditEvents.UpdateDescriptionContent(value = value))
        },
        onFocusChangeListener = { focusState ->
            adEditEvents(AdEditEvents.ChangeDescriptionState(focusState = focusState))
        }
    )
}

