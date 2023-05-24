package com.alitafreshi.room_db.task

import androidx.room.*
import com.alitafreshi.room_db.task.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM note WHERE isRemoved = 0 AND localId IS NOT NULL")
    fun getAllNotes(): Flow<List<Note>>

    @Query("SELECT * FROM note WHERE isRemoved = 1 AND localId IS NOT NULL AND remoteId IS NOT NULL")
    suspend fun getAllRemovedNotes(): List<Note>

    @Query("SELECT * FROM note WHERE localId = :id")
    suspend fun getNoteById(id: Int): Note?

    @Upsert
    suspend fun insertNewNote(note: Note)

    @Upsert
    suspend fun insertNewNoteList(notes: List<Note>): List<Long>

    @Query("UPDATE Note SET isRemoved = 1 WHERE localId IN (:notesId)")
    suspend fun updateRemoveStatus(notesId: List<Int>)

    @Delete
    suspend fun deleteNotes(notes: List<Note>)

    @Query("DELETE FROM note")
    suspend fun clearAllNotes()

}