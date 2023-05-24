package com.alitafreshi.data.datasource

import com.alitafreshi.data.qualifier.IoDispatcher
import com.alitafreshi.domain.interactors.remote.handleFlowRequestState
import com.alitafreshi.domain.interactors.remote.handleRequestState
import com.alitafreshi.domain.model.toNoteDto
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


    override suspend fun getSyncedNoteList(): Flow<List<Note>> = noteRepository.getNotes()
        .combine(
            noteRemoteRepository.getNotesByUserId().handleFlowRequestState()
                .catch { emit(emptyList()) }.flowOn(ioDispatcher)
        ) { localNoteList, remoteNotes ->

            if (localNoteList.isEmpty() && remoteNotes.isNotEmpty()) {
                noteRepository.insertNoteList(notes = remoteNotes.toNoteList())
            }
            CoroutineScope(ioDispatcher).launch {
                if (localNoteList.isNotEmpty() && remoteNotes.isNotEmpty()) {

                    /** COMPARE LOCAL AND REMOTE NOTE WITH EACH OTHER AND  FLOW THIS STEPS :
                     *
                     * 1 - if any localNote ID is Null And The IsRemoved Flag Is False We should send it to the remote and get remote notes again and update the cache
                     *
                     * 2 - if local and remote note list aren't the same and the And The IsRemoved Flag ّFor local notes is false or in the other hand the cache has some extra note items that their id is null and the  And The IsRemoved Flag is False we should update the remote with this notes
                     *
                     * Important Note : Step 1 And 2 Are The same Thing
                     *
                     * 3 - check if any local notes contains isRemoved True We should remove it from remote and then remove it from the cache completely
                     *
                     *
                     * */

                    val unSyncedLocalNotes =
                        localNoteList.filter { cachedNote -> cachedNote.remoteId == null && !cachedNote.isRemoved }
                    if (unSyncedLocalNotes.isNotEmpty()) {
                        val syncedNotes = mutableListOf<Note>()
                        unSyncedLocalNotes.forEach {
                            val remoteNote = noteRemoteRepository.insertNewNote(note = it.toNoteDto())
                                .handleRequestState(successStatusCode = 201)
                            syncedNotes.add(it.copy(remoteId = remoteNote.noteId))
                        }
                        noteRepository.insertNoteList(notes = syncedNotes)
                    }

                    val removedLocalNotes = noteRepository.getRemovedNotes()
                    if (removedLocalNotes.isNotEmpty()) {
                        val removedNotesFromRemote = mutableListOf<Note>()
                        removedLocalNotes.forEach { localRemovedNote ->
                            localRemovedNote.remoteId?.let { removedNoteId ->
                                val response = noteRemoteRepository.removeNote(noteId = removedNoteId)
                                    .handleRequestState()
                                if (!response.isNullOrEmpty() && localRemovedNote.localId != null) {
                                    removedNotesFromRemote.add(localRemovedNote)
                                }
                            }
                        }
                        if (removedNotesFromRemote.isNotEmpty()) {
                            noteRepository.deleteNotes(notes = removedNotesFromRemote)
                        }
                    }
                }
            }

            localNoteList

        }.flowOn(ioDispatcher)
}