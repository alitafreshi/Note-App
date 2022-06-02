package com.alitafreshi.task_add_edit.view_state

data class AdEditViewState(
    val noteId: Int? = null,
    val taskAdEditTitleTextFieldState: TaskAdEditTextFieldState = TaskAdEditTextFieldState(
        hint = "موضوع یادداشت"
    ),
    val taskAdEditDescriptionTextFieldState: TaskAdEditTextFieldState = TaskAdEditTextFieldState(
        hint = "توضیحات یادداشتت رو اینجا بنویس..."
    ),
)
