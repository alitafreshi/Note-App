package com.alitafreshi.task_list.presentation

import com.alitafreshi.room_db.task.model.Note


sealed class TaskListEvents {
    data class DeleteNotes(val deletedNotes: List<Int>) : TaskListEvents()
    data class RestoreNote(val restoredNotes: List<Note>) : TaskListEvents()
    object RetrieveNoteList : TaskListEvents()
    data class AddToSelectionList(val note: Note) : TaskListEvents()
    data class NavigateToNoteAddEditFragment(val noteId: Int) : TaskListEvents()
}
