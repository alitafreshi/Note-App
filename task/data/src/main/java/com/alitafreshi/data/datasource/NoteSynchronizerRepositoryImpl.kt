package com.alitafreshi.data.datasource

import com.alitafreshi.data.qualifier.IoDispatcher
import com.alitafreshi.domain.interactors.remote.handleFlowRequestState
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
        ) { localNoteList, remoteNotes ->

            if (localNoteList.isEmpty() && remoteNotes.isNotEmpty()) {
                noteRepository.clearAllNotes()
                noteRepository.insertNoteList(notes = remoteNotes.toNoteList())
            }

            if (localNoteList.isNotEmpty() && remoteNotes.isNotEmpty()) {

                /** COMPARE LOCAL AND REMOTE NOTE WITH EACH OTHER AND  FLOW THIS STEPS :
                 *
                 * 1 - if any localNote ID is Null And The IsRemoved Flag Is False We should send it to the remote and get remote notes again and update the cache
                 *
                 * 2 - if local and remote note list aren't the same and the And The IsRemoved Flag ّFor local notes is false or in the other hand the cache has some extra note items that their id is null and the  And The IsRemoved Flag is False we should update the remote with this notes
                 *
                 * Important Note : Step 1 And 2 Are The same Thing
                 *
                 *
                 * */

                val unSyncedLocalNotes =
                    localNoteList.filter { cachedNote -> cachedNote.id == null && !cachedNote.isRemoved }

                if (unSyncedLocalNotes.isNotEmpty()) {
                    unSyncedLocalNotes.forEach {
                        noteRemoteRepository.insertNewNote(note = it.toNoteDto())
                    }
                }

            }

            localNoteList


        }
}