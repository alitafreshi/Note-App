package com.alitafreshi.data.datasource.remote.repository

import com.alitafreshi.constance.Constance.NOTE_FEATURE_BASE_URL
import com.alitafreshi.domain.model.NoteDto
import com.alitafreshi.domain.remote.BaseResponse
import com.alitafreshi.domain.repository.remote.NoteRemoteRepository
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class NoteRemoteRepositoryImpl(private val httpClient: HttpClient) :
    NoteRemoteRepository {


    override suspend fun getNotesByUserId(userId: Long): Flow<BaseResponse<List<NoteDto>>> =
        flow {
            emit(httpClient.prepareGet {
                url {
                    appendEncodedPathSegments(NOTE_FEATURE_BASE_URL, "noteList", "$userId")

                }
            }.body())
        }


    /*override suspend fun insertNewNote(note: NoteDto): Flow<HttpResponse<BaseResponse<NoteDto>>> {
        TODO("Not yet implemented")
    }

    override suspend fun updateNote(
        noteId: Long,
        note: NoteDto
    ): Flow<HttpResponse<BaseResponse<NoteDto>>> {
        TODO("Not yet implemented")
    }

    override suspend fun removeNote(noteId: Long): Flow<HttpResponse<BaseResponse<String>>> {

    }*/

}