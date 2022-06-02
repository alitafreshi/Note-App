package com.alitafreshi.data.datasource.local.room.repository

import com.alitafreshi.data.datasource.local.room.NoteDao
import com.alitafreshi.domain.model.Note
import com.alitafreshi.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(private val noteDao: NoteDao) : NoteRepository {

    override fun getNotes(): Flow<List<Note>> = noteDao.getAllNotes()

    override suspend fun getNoteById(id: Int): Note? = noteDao.getNoteById(id = id)

    override suspend fun insertNote(note: Note) = noteDao.insertNewNote(note = note)

    override suspend fun insertNoteList(notes: List<Note>) =
        noteDao.insertNewNoteList(notes = notes)


    override suspend fun deleteNotes(notes: List<Note>) = noteDao.deleteNote(notes = notes)

}