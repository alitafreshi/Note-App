package com.alitafreshi.task_list.presentation

import com.alitafreshi.domain.model.Note
import com.alitafreshi.domain.util.NoteOrder
import com.alitafreshi.domain.util.OrderType
import ir.tafreshiali.ayan_core.util.BottomSheetState

data class TaskListViewState(
    val taskList: List<Note> = emptyList(),
    val selectedTaskList: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isAddButtonVisible: Boolean = true,
    val loadingState: BottomSheetState = BottomSheetState.Idle
)
