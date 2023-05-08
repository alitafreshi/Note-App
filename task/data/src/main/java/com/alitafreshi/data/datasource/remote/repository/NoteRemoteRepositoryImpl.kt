package com.alitafreshi.data.datasource.remote.repository

import com.alitafreshi.domain.model.NoteDto
import com.alitafreshi.domain.remote.BaseResponse
import com.alitafreshi.domain.repository.remote.NoteRemoteRepository
import com.alitafreshi.domain.repository.remote.NoteRemoteService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Call
import retrofit2.Response

class NoteRemoteRepositoryImpl(private val noteRemoteService: NoteRemoteService) :
    NoteRemoteRepository {

    override suspend fun getNotesByUserId(userId: Long): Flow<Call<BaseResponse<List<NoteDto>>>> =
        flow {
            emit(noteRemoteService.getNotesByUserId())
        }


    override suspend fun insertNewNote(note: NoteDto): Flow<Response<BaseResponse<NoteDto>>> =
        flow {
            emit(noteRemoteService.insertNewNote(note = note))
        }


    override suspend fun updateNote(
        noteId: Long,
        note: NoteDto
    ): Flow<Response<BaseResponse<NoteDto>>> = flow {
        emit(noteRemoteService.updateNote(noteId = noteId, note = note))
    }


    override suspend fun removeNote(noteId: Long): Flow<Response<BaseResponse<String>>> = flow {
        emit(
            noteRemoteService.removeNote(noteId = noteId)
        )
    }

}