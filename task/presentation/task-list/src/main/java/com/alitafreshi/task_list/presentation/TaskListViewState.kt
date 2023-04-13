package com.alitafreshi.task_list.presentation

import com.alitafreshi.domain.LoadingState
import com.alitafreshi.domain.util.NoteOrder
import com.alitafreshi.domain.util.OrderType
import com.alitafreshi.room_db.task.model.Note


data class TaskListViewState(
    val taskList: List<Note> = emptyList(),
    val selectedTaskList: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isAddButtonVisible: Boolean = true,
    val loadingState: LoadingState = LoadingState.Idle
)
