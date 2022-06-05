package com.alitafreshi.noteapp.presentation.navigation.destinations.task.task_ad_edit

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.alitafreshi.components.util.spacing
import com.alitafreshi.task_add_edit.TaskAdEditScreen
import com.alitafreshi.task_add_edit.UiEvents
import com.alitafreshi.task_add_edit.view_event.AdEditEvents
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import kotlinx.coroutines.flow.collectLatest

@RootNavGraph
@Destination(navArgsDelegate = AdEditNavArgs::class)
@Composable
fun TaskAdEditDestination(
    navigateBack: () -> Unit
) {

    val scaffoldState = rememberScaffoldState()

    val taskAdEditViewModel: AdEditViewModel = hiltViewModel()

    LaunchedEffect(key1 = true) {
        taskAdEditViewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvents.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is UiEvents.SaveNote -> {
                    navigateBack()
                }
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState
    ) {
        TaskAdEditScreen(
            modifier = Modifier.padding(MaterialTheme.spacing.default),
//            taskAdEditTitleTextFieldState = taskAdEditViewModel.getCurrentViewStateOrNew().taskAdEditTitleTextFieldState,
//            taskAdEditDescriptionTextFieldState = taskAdEditViewModel.getCurrentViewStateOrNew().taskAdEditDescriptionTextFieldState,
            taskAdEditTitleText = taskAdEditViewModel.getCurrentViewStateOrNew().taskAdEditTitleTextFieldState.text,
            taskAdEditTitleHintText = taskAdEditViewModel.getCurrentViewStateOrNew().taskAdEditTitleTextFieldState.hint,
            taskAdEditTitleIsHintVisible = taskAdEditViewModel.getCurrentViewStateOrNew().taskAdEditTitleTextFieldState.isHintEnabled,
            taskAdEditDescriptionText = taskAdEditViewModel.getCurrentViewStateOrNew().taskAdEditDescriptionTextFieldState.text,
            taskAdEditDescriptionHintText = taskAdEditViewModel.getCurrentViewStateOrNew().taskAdEditDescriptionTextFieldState.hint,
            taskAdEditDescriptionIsHintVisible = taskAdEditViewModel.getCurrentViewStateOrNew().taskAdEditDescriptionTextFieldState.isHintEnabled,
            adEditEvents = taskAdEditViewModel::onTriggerEvent,
            navigateBack = {
                if (taskAdEditViewModel.getCurrentViewStateOrNew().taskAdEditTitleTextFieldState.text.isNotEmpty() && taskAdEditViewModel.getCurrentViewStateOrNew().taskAdEditDescriptionTextFieldState.text.isNotEmpty()) taskAdEditViewModel.onTriggerEvent(
                    event = AdEditEvents.SaveNote
                ) else navigateBack()
            }
        )
    }
}