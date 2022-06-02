package com.alitafreshi.task_list.presentation

import com.alitafreshi.domain.model.Note
import com.alitafreshi.domain.uti.NoteOrder
import com.alitafreshi.domain.uti.OrderType

data class TaskListViewState(
    val taskList: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isAddButtonVisible: Boolean = true,
)
