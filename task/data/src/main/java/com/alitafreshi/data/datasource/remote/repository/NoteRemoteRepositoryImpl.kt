package com.alitafreshi.data.datasource.remote.repository

import com.alitafreshi.domain.model.NoteDto
import com.alitafreshi.domain.remote.BaseResponse
import com.alitafreshi.domain.repository.remote.NoteRemoteRepository
import com.alitafreshi.domain.repository.remote.NoteRemoteService
import kotlinx.coroutines.flow.Flow

class NoteRemoteRepositoryImpl(private val noteRemoteService: NoteRemoteService) :
    NoteRemoteRepository {

    override suspend fun getNotesByUserId(userId: Long): Flow<BaseResponse<List<NoteDto>>> =
        noteRemoteService.getNotesByUserId()


    override suspend fun insertNewNote(note: NoteDto): Flow<BaseResponse<NoteDto>> =
        noteRemoteService.insertNewNote(note = note)


    override suspend fun updateNote(noteId: Long, note: NoteDto): Flow<BaseResponse<NoteDto>> =
        noteRemoteService.updateNote(noteId = noteId, note = note)


    override suspend fun removeNote(noteId: Long): Flow<BaseResponse<String>> =
        noteRemoteService.removeNote(noteId = noteId)

}