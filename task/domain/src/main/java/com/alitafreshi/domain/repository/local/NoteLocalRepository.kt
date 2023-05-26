package com.alitafreshi.domain.repository.local

import com.alitafreshi.room_db.task.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteLocalRepository {
    fun getNotes(): Flow<List<Note>>
    suspend fun getUnSyncedNotes(): List<Note>
    suspend fun getRemovedNotes(): List<Note>
    suspend fun getNoteById(id: Int): Note?
    suspend fun insertNote(note: Note)
    suspend fun insertNoteList(notes: List<Note>): List<Long>
    suspend fun updateRemoveStatus(notesId: List<Int>)
    suspend fun deleteNotes(notes: List<Note>)
    suspend fun clearAllNotes()
}