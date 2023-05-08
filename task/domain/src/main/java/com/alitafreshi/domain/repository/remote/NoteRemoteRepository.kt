package com.alitafreshi.domain.repository.remote

import com.alitafreshi.domain.model.NoteDto
import com.alitafreshi.domain.remote.BaseResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Response

interface NoteRemoteRepository {
    suspend fun getNotesByUserId(userId: Long = 1): Flow<Call<Response<BaseResponse<List<NoteDto>>>>>

    suspend fun insertNewNote(note: NoteDto): Flow<Response<BaseResponse<NoteDto>>>

    suspend fun updateNote(noteId: Long, note: NoteDto): Flow<Response<BaseResponse<NoteDto>>>

    suspend fun removeNote(noteId: Long): Flow<Response<BaseResponse<String>>>
}