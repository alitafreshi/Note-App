package com.alitafreshi.data.datasource.local.repository


import com.alitafreshi.domain.repository.NoteRepository
import com.alitafreshi.room_db.task.NoteDao
import com.alitafreshi.room_db.task.model.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

class NoteRepositoryImpl(private val noteDao: NoteDao) : NoteRepository {

    override suspend fun getNotes(): Flow<List<Note>> = noteDao.getAllNotes()

    override suspend fun getNoteById(id: Int): Note? = noteDao.getNoteById(id = id)

    override suspend fun insertNote(note: Note) {
        noteDao.insertNewNote(note = note)
    }

    override suspend fun insertNoteList(notes: List<Note>) =
        noteDao.insertNewNoteList(notes = notes)


    override suspend fun deleteNotes(notes: List<Note>) = noteDao.deleteNote(notes = notes)
    override suspend fun clearAllNotes() {
        noteDao.clearAllNotes()
    }

}