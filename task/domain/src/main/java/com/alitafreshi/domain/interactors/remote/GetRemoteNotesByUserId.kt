package com.alitafreshi.domain.interactors.remote

import com.alitafreshi.data.qualifier.IoDispatcher
import com.alitafreshi.domain.model.toNoteList
import com.alitafreshi.domain.remote.BaseResponse
import com.alitafreshi.domain.remote.UnExpectedApiResponseException
import com.alitafreshi.domain.repository.local.NoteLocalRepository
import com.alitafreshi.domain.repository.remote.NoteRemoteRepository
import com.alitafreshi.room_db.task.model.Note
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform

class GetRemoteNotesByUserId(
    private val noteRemoteRepository: NoteRemoteRepository,
    private val noteRepository: NoteLocalRepository,
    @IoDispatcher val ioDispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(): Flow<List<Note>> =
        noteRemoteRepository.getNotesByUserId().handleRequestState()
            .map { noteList ->
                updateLocalCache(remoteNoteList = noteList.toNoteList())
                noteList.toNoteList()
            }.flowOn(ioDispatcher)

    private suspend fun updateLocalCache(remoteNoteList: List<Note>) {
        noteRepository.clearAllNotes()
        noteRepository.insertNoteList(notes = remoteNoteList)
    }
}

inline fun <reified T> Flow<BaseResponse<T>>.handleRequestState(successStatusCode: Int = 200): Flow<T> =
    transform { response ->
        when (response.status.code) {
            successStatusCode -> {
                emit(response.response)
            }
            else -> {
                throw (UnExpectedApiResponseException(status = response.status))
            }
        }
    }



