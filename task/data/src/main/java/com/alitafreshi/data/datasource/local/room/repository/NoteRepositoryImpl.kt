package com.alitafreshi.data.datasource.local.room.repository

import com.alitafreshi.data.datasource.local.room.NoteDao
import com.alitafreshi.domain.model.Note
import com.alitafreshi.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(private val noteDao: NoteDao) : NoteRepository {

    override fun getNotes(): Flow<List<Note>> = noteDao.getAllNotes()

    override suspend fun getNoteById(id: Int): Note? = noteDao.getNoteById(id = id)

    override suspend fun insertNote(note: Note) = noteDao.insert(note = note)

    override suspend fun deleteNote(note: Note) = noteDao.deleteNote(note = note)

}