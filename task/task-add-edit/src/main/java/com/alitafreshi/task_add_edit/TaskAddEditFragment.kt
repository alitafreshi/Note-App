package com.alitafreshi.task_add_edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.alitafreshi.task_add_edit.view_event.AdEditEvents
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TaskAddEditFragment : Fragment() {

    private val taskAddEditViewModel: AddEditViewModel by hiltNavGraphViewModels(R.id.task_add_edit_nav_graph)

    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ComposeView(context = requireContext()).apply {
        setContent {

            val scaffoldState = rememberScaffoldState()
            val focusManager = LocalFocusManager.current
            val keyboardController = LocalSoftwareKeyboardController.current


            Scaffold(
                scaffoldState = scaffoldState
            ) {

                TaskAdEditScreen(
                    //TODO WHY WE SHOULD PASS THIS PADDING VALUES WHEN USING SCAFFOLD
                    //modifier = Modifier.padding(MaterialTheme.spacing.default),
                    modifier = Modifier.padding(it),
                    taskAdEditTitleTextFieldState = taskAddEditViewModel.getCurrentViewStateOrNew().taskAdEditTitleTextFieldState,
                    taskAdEditDescriptionTextFieldState = taskAddEditViewModel.getCurrentViewStateOrNew().taskAdEditDescriptionTextFieldState,
                    adEditEvents = taskAddEditViewModel::onTriggerEvent,
                    navigateBack = {
                        focusManager.clearFocus()
                        keyboardController?.hide()
                        taskAddEditViewModel.onTriggerEvent(event = AdEditEvents.SaveNote)
                    }
                )
            }
        }
    }
}