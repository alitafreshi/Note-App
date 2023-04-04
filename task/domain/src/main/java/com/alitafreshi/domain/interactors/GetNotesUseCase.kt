package com.alitafreshi.domain.interactors

import com.alitafreshi.domain.DataState
import com.alitafreshi.domain.LoadingState
import com.alitafreshi.domain.model.Note
import com.alitafreshi.domain.repository.NoteRepository
import com.alitafreshi.domain.util.NoteOrder
import com.alitafreshi.domain.util.OrderType

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.transform

class GetNotesUseCase(private val noteRepository: NoteRepository) {

    operator fun invoke(noteOrder: NoteOrder = NoteOrder.Date(orderType = OrderType.Descending)): Flow<DataState<List<Note>>> =
        noteRepository.getNotes().transform { noteList ->
            emit(DataState.Data(data = noteList))
            emit(DataState.Loading(loadingState = LoadingState.Idle))
        }
            .onStart { emit(DataState.Loading(loadingState = LoadingState.Loading)) }

            .catch { emit(DataState.Error(errorMessage = "Some Think Went Wrong")) }

}