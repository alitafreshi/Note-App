package com.alitafreshi.data.datasource.local.repository


import com.alitafreshi.domain.repository.local.NoteLocalRepository
import com.alitafreshi.room_db.task.NoteDao
import com.alitafreshi.room_db.task.model.Note

class NoteLocalRepositoryImpl(private val noteDao: NoteDao) : NoteLocalRepository {

    override suspend fun getNotes(): List<Note> = noteDao.getAllNotes()

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