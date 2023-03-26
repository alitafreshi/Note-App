package com.alitafreshi.data.datasource.local.room

import androidx.room.*
import com.alitafreshi.domain.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM note")
    fun getAllNotes(): Flow<List<Note>>

    @Query("SELECT * FROM note WHERE id = :id")
    suspend fun getNoteById(id: Int): Note?

    @Upsert
    suspend fun insertNewNote(note: Note): Long

    @Upsert
    suspend fun insertNewNoteList(notes: List<Note>): List<Long>

    @Delete
    suspend fun deleteNote(notes: List<Note>):Int

}