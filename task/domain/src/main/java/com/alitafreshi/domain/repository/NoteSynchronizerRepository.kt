package com.alitafreshi.domain.repository

import com.alitafreshi.room_db.task.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteSynchronizerRepository {
    suspend fun getSyncedNoteList():Flow<List<Note>>

}