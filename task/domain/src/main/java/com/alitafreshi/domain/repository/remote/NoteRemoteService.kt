package com.alitafreshi.domain.repository.remote

import com.alitafreshi.constance.Constance.NOTE_FEATURE_BASE_URL
import com.alitafreshi.domain.model.NoteDto
import com.alitafreshi.domain.remote.BaseResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.*

interface NoteRemoteService {

    @GET("$NOTE_FEATURE_BASE_URL/noteList/{userId}")
    suspend fun getNotesByUserId(@Path("userId") userId: Long = 1): Flow<BaseResponse<List<NoteDto>>>

    @POST("$NOTE_FEATURE_BASE_URL/newNote")
    suspend fun insertNewNote(@Body note: NoteDto): Flow<BaseResponse<NoteDto>>

    @POST("$NOTE_FEATURE_BASE_URL/updateNote/{noteId}")
    suspend fun updateNote(
        @Path("noteId") noteId: Long,
        @Body note: NoteDto
    ): Flow<BaseResponse<NoteDto>>

    @DELETE("$NOTE_FEATURE_BASE_URL/deleteNote/{noteId}")
    suspend fun removeNote(@Path("noteId") noteId: Long):Flow<BaseResponse<String>>

}