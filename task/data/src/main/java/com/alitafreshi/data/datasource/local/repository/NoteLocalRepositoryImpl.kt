package com.alitafreshi.data.datasource.local.repository


import com.alitafreshi.domain.repository.local.NoteLocalRepository
import com.alitafreshi.room_db.task.NoteDao
import com.alitafreshi.room_db.task.model.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

class NoteLocalRepositoryImpl(private val noteDao: NoteDao) : NoteLocalRepository {

    override fun getNotes(): Flow<List<Note>> = noteDao.getAllNotes().distinctUntilChanged()
    override suspend fun getRemovedNotes(): List<Note> = noteDao.getAllRemovedNotes()

    override suspend fun getNoteById(id: Int): Note? = noteDao.getNoteById(id = id)

    override suspend fun insertNote(note: Note) {
        noteDao.insertNewNote(note = note)
    }

    override suspend fun insertNoteList(notes: List<Note>) =
        noteDao.insertNewNoteList(notes = notes)

    override suspend fun updateRemoveStatus(notesId: List<Int>) {
        noteDao.updateRemoveStatus(notesId = notesId)
    }

    override suspend fun deleteNotes(notes: List<Note>) {
        noteDao.deleteNotes(notes = notes)
    }

    override suspend fun clearAllNotes() {
        noteDao.clearAllNotes()
    }

}