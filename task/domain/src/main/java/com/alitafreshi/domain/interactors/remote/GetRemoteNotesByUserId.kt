package com.alitafreshi.domain.interactors.remote

import com.alitafreshi.data.qualifier.IoDispatcher
import com.alitafreshi.domain.DataState
import com.alitafreshi.domain.model.toNote
import com.alitafreshi.domain.remote.BaseResponse
import com.alitafreshi.domain.repository.remote.NoteRemoteRepository
import com.alitafreshi.room_db.task.model.Note
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform
import retrofit2.Call
import retrofit2.Response
import retrofit2.awaitResponse

class GetRemoteNotesByUserId(
    private val noteRemoteRepository: NoteRemoteRepository,
    @IoDispatcher val ioDispatcher: CoroutineDispatcher
) {

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
            }.flowOn(ioDispatcher)
}

inline fun <reified T> Flow<Call<Response<BaseResponse<T>>>>.handleRequestState(successStatusCode: Int = 200): Flow<DataState<T>> =
    transform { response ->
        when {
            response.awaitResponse().
            response.body() != null && response.code() == successStatusCode -> {
                emit(DataState.Data(response.body()!!.response))
            }
            else -> {
                response.body()?.let { baseResponse ->
                    emit(DataState.Error(errorMessage = baseResponse.status!!.description))
                } ?: emit(DataState.Error(errorMessage = response.code().toString()))
            }
        }
    }



