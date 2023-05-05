package com.alitafreshi.domain.interactors.remote

import com.alitafreshi.domain.DataState
import com.alitafreshi.domain.model.toNote
import com.alitafreshi.domain.remote.BaseResponse
import com.alitafreshi.domain.repository.remote.NoteRemoteRepository
import com.alitafreshi.room_db.task.model.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform

class GetRemoteNotesByUserId(private val noteRemoteRepository: NoteRemoteRepository) {

    suspend operator fun invoke(): Flow<DataState<List<Note>>> =
        noteRemoteRepository.getNotesByUserId()
            .handleRequestState().map { dataState ->
                when (dataState) {
                    is DataState.Error -> {
                        DataState.Error(dataState.errorMessage)
                    }
                    is DataState.Data -> {
                        val mappedData = dataState.data?.map { noteDto -> noteDto.toNote() }
                        DataState.Data(mappedData)
                    }
                    is DataState.Loading -> {
                        DataState.Loading(dataState.loadingState)
                    }
                }
            }
}

inline fun <reified T> Flow<BaseResponse<T>>.handleRequestState(successStatusCode: Int = 200): Flow<DataState<T>> =
    transform { response ->
        when (response.status.code) {
            successStatusCode -> {
                emit(DataState.Data(response.response))
            }
            else -> {
                emit(DataState.Error(errorMessage = response.status.description))
            }
        }
    }



