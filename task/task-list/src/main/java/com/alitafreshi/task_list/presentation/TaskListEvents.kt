package com.alitafreshi.task_list.presentation

import com.alitafreshi.domain.model.Note

sealed class TaskListEvents {
    data class DeleteNotes(val deletedNotes: List<Note>) : TaskListEvents()
    data class RestoreNote(val restoredNotes: List<Note>) : TaskListEvents()
    object RetrieveNoteList : TaskListEvents()
    data class AddToSelectionList(val note: Note) : TaskListEvents()
}
