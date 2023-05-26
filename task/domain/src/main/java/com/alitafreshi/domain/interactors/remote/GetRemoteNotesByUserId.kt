package com.alitafreshi.domain.interactors.remote

import com.alitafreshi.data.qualifier.IoDispatcher
import com.alitafreshi.domain.model.toNoteList
import com.alitafreshi.domain.remote.BaseResponse
import com.alitafreshi.domain.remote.UnExpectedApiResponseException
import com.alitafreshi.domain.repository.remote.NoteRemoteRepository
import com.alitafreshi.room_db.task.model.Note
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform

class GetRemoteNotesByUserId(
    private val noteRemoteRepository: NoteRemoteRepository,
    @IoDispatcher val ioDispatcher: CoroutineDispatcher
) {
    operator fun invoke(): Flow<List<Note>> =
        noteRemoteRepository.getNotesByUserId().handleFlowRequestState().map { it.toNoteList() }
}

inline fun <reified T> Flow<BaseResponse<T>>.handleFlowRequestState(successStatusCode: Int = 200): Flow<T> =
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

inline fun <reified T> BaseResponse<T>.handleRequestState(successStatusCode: Int = 200): T? =
    when (this.status.code) {
        successStatusCode -> this.response
        else -> null
    }




