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
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                TransparentHintTextField(
                    modifier=Modifier.weight(3f),
                    text = taskAdEditTitleTextFieldState.text,
                    hint = taskAdEditTitleTextFieldState.hint,
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

                 Icon(
                      modifier = Modifier.weight(0.5f).clickable(onClick = navigateBack),
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "btn back"
                 )
            }
        }

        item {
            TransparentHintTextField(
                modifier = Modifier.fillMaxWidth(),
                text = taskAdEditDescriptionTextFieldState.text,
                hint = taskAdEditDescriptionTextFieldState.hint,
                isHintVisible = taskAdEditDescriptionTextFieldState.isHintEnabled,
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