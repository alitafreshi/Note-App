package com.alitafreshi.domain.repository

import com.alitafreshi.room_db.task.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun getNotes(): Flow<List<Note>>
    suspend fun getNoteById(id: Int): Note?
    suspend fun insertNote(note: Note)
    suspend fun insertNoteList(notes: List<Note>): List<Long>
    suspend fun deleteNotes(notes: List<Note>): Int
}