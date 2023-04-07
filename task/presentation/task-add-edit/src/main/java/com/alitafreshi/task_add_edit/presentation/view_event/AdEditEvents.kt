package com.alitafreshi.task_add_edit.presentation.view_event

import androidx.compose.ui.focus.FocusState

sealed class AdEditEvents {
    data class UpdateTitleContent(val value: String) : AdEditEvents()
    data class ChangeTitleFocusState(val focusState: FocusState) : AdEditEvents()
    data class UpdateDescriptionContent(val value: String) : AdEditEvents()
    data class ChangeDescriptionState(val focusState: FocusState) : AdEditEvents()
    object SaveNote : AdEditEvents()

    data class GetTaskById(val taskId: Int) : AdEditEvents()
}
