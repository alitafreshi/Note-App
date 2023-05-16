package com.alitafreshi.data.datasource

import com.alitafreshi.data.qualifier.IoDispatcher
import com.alitafreshi.domain.repository.NoteRepository
import com.alitafreshi.domain.repository.NoteSynchronizerRepository
import com.alitafreshi.domain.repository.remote.NoteRemoteRepository
import com.alitafreshi.room_db.task.model.Note
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.future.asCompletableFuture
import kotlinx.coroutines.withContext

class NoteSynchronizerRepositoryImpl(
    private val noteRepository: NoteRepository,
    private val noteRemoteRepository: NoteRemoteRepository,
    @IoDispatcher val ioDispatcher: CoroutineDispatcher
) : NoteSynchronizerRepository {

    override suspend fun getSyncedNoteList(): Flow<List<Note>> =
        withContext(context = ioDispatcher) {

            val remoteNoteList = async { noteRemoteRepository.getNotesByUserId() }
            remoteNoteList.await()

                        awaitAll(async { noteRepository.clearAllNotes() },async { noteRepository.insertNoteList(notes = ) })

            noteRepository.getNotes()
        }
}