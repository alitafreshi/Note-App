package com.alitafreshi.domain.repository.remote

import com.alitafreshi.domain.model.NoteDto
import com.alitafreshi.domain.remote.BaseResponse
import io.ktor.client.statement.*
import kotlinx.coroutines.flow.Flow



interface NoteRemoteRepository {
    suspend fun getNotesByUserId(userId: Long = 1): Flow<BaseResponse<List<NoteDto>>>

    suspend fun insertNewNote(note: NoteDto): Flow<BaseResponse<NoteDto>>

    /*suspend fun updateNote(noteId: Long, note: NoteDto): Flow<BaseResponse<NoteDto>>

    suspend fun removeNote(noteId: Long): Flow<BaseResponse<String>>*/
}