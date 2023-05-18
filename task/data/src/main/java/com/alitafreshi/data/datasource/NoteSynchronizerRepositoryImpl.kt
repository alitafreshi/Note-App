package com.alitafreshi.data.datasource

import com.alitafreshi.data.qualifier.IoDispatcher
import com.alitafreshi.domain.interactors.remote.handleRequestState
import com.alitafreshi.domain.model.toNoteList
import com.alitafreshi.domain.repository.local.NoteLocalRepository
import com.alitafreshi.domain.repository.NoteSynchronizerRepository
import com.alitafreshi.domain.repository.remote.NoteRemoteRepository
import com.alitafreshi.room_db.task.model.Note
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class NoteSynchronizerRepositoryImpl(
    private val noteRepository: NoteLocalRepository,
    private val noteRemoteRepository: NoteRemoteRepository,
    @IoDispatcher val ioDispatcher: CoroutineDispatcher
) : NoteSynchronizerRepository {
    override suspend fun getSyncedNoteList(): Flow<List<Note>> = flow {
        val localNotes = noteRepository.getNotes()

        emit(localNotes)

        val remoteNotes = noteRemoteRepository.getNotesByUserId().handleRequestState().toNoteList()
        noteRepository.clearAllNotes()
        noteRepository.insertNoteList(notes = remoteNotes)

        emit(localNotes)

    }.flowOn(ioDispatcher)
}