package com.alitafreshi.noteapp.presentation.navigation.destinations.task.task_ad_edit

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.hilt.navigation.compose.hiltViewModel
import com.alitafreshi.components.util.spacing
import com.alitafreshi.task_add_edit.TaskAdEditScreen
import com.alitafreshi.task_add_edit.UiEvents
import com.alitafreshi.task_add_edit.view_event.AdEditEvents
import com.alitafreshi.task_add_edit.view_state.TaskAdEditTextFieldState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import kotlinx.coroutines.flow.collectLatest

@ExperimentalComposeUiApi
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

    TaskAdEditMainContent(
        scaffoldState =scaffoldState ,
        taskAdEditTitleTextFieldState =taskAdEditViewModel.getCurrentViewStateOrNew().taskAdEditTitleTextFieldState ,
        taskAdEditDescriptionTextFieldState = taskAdEditViewModel.getCurrentViewStateOrNew().taskAdEditDescriptionTextFieldState,
        adEditEvents = taskAdEditViewModel::onTriggerEvent
    )

}

@ExperimentalComposeUiApi
@Composable
fun TaskAdEditMainContent(
    scaffoldState: ScaffoldState,
    taskAdEditTitleTextFieldState: TaskAdEditTextFieldState,
    taskAdEditDescriptionTextFieldState: TaskAdEditTextFieldState,
    adEditEvents: (AdEditEvents) -> Unit,
) {

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        scaffoldState = scaffoldState
    ) {
        TaskAdEditScreen(
            modifier = Modifier.padding(MaterialTheme.spacing.default),
            taskAdEditTitleTextFieldState = taskAdEditTitleTextFieldState,
            taskAdEditDescriptionTextFieldState = taskAdEditDescriptionTextFieldState,
            adEditEvents = adEditEvents,
            navigateBack = {
                focusManager.clearFocus()
                keyboardController?.hide()
                adEditEvents(AdEditEvents.SaveNote)
            }
        )
    }
}