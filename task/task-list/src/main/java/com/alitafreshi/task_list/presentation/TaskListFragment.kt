package com.alitafreshi.task_list.presentation

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.findNavController
import com.alitafreshi.task_list.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TaskListFragment : Fragment() {

    val taskListViewModel: TaskListViewModel by hiltNavGraphViewModels(R.id.task_list_nav_graph)


    @ExperimentalFoundationApi
    @ExperimentalAnimationApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ComposeView(context = requireContext()).apply {
        setContent {
            TaskListScreen(
                taskListViewState = taskListViewModel.getCurrentViewStateOrNew(),
                taskListStateEvents = taskListViewModel::onTriggerEvent,
                taskBackGroundColor = Color(0xFFEFF2F9),
                descriptionTextStyle = MaterialTheme.typography.subtitle1.copy(
                    color = Color(0xFFA3A3A3)
                ),
                navigateToAddNewTask = { noteId ->
                    val uri = Uri.parse("https://tafreshiali.ir/tasks/$noteId")
                    findNavController().navigate(uri)
                }
            )
        }
    }

}