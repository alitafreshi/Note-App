package com.alitafreshi.data.datasource

import com.alitafreshi.data.qualifier.IoDispatcher
import com.alitafreshi.domain.interactors.remote.handleRequestState
import com.alitafreshi.domain.model.toNoteList
import com.alitafreshi.domain.repository.NoteRepository
import com.alitafreshi.domain.repository.NoteSynchronizerRepository
import com.alitafreshi.domain.repository.remote.NoteRemoteRepository
import com.alitafreshi.room_db.task.model.Note
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NoteSynchronizerRepositoryImpl(
    private val noteRepository: NoteRepository,
    private val noteRemoteRepository: NoteRemoteRepository,
    @IoDispatcher val ioDispatcher: CoroutineDispatcher
) : NoteSynchronizerRepository {

    override suspend fun getSyncedNoteList(): Flow<List<Note>> =
       withContext(context = ioDispatcher) {

            launch {
                noteRemoteRepository.getNotesByUserId().handleRequestState()
                    .map { noteList ->
                        noteRepository.clearAllNotes()
                        noteRepository.insertNoteList(notes = noteList.toNoteList())
                    }.flowOn(ioDispatcher).launchIn(this)
            }

            noteRepository.getNotes()
        }
}