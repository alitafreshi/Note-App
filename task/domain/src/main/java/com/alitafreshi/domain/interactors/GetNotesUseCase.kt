package com.alitafreshi.domain.interactors

import com.alitafreshi.domain.DataState
import com.alitafreshi.domain.LoadingState
import com.alitafreshi.domain.interactors.remote.GetRemoteNotesByUserId
import com.alitafreshi.domain.repository.NoteRepository
import com.alitafreshi.domain.util.NoteOrder
import com.alitafreshi.domain.util.OrderType
import com.alitafreshi.room_db.task.model.Note
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

class GetNotesUseCase(
    private val noteRepository: NoteRepository,
    private val getRemoteNotesByUserId: GetRemoteNotesByUserId
) {

    @OptIn(FlowPreview::class)
    suspend operator fun invoke(noteOrder: NoteOrder = NoteOrder.Date(orderType = OrderType.Descending)): Flow<DataState<List<Note>>> =
        noteRepository.getNotes().flatMapConcat { localNoteList ->
            getRemoteNotesByUserId().transform { remoteDataState ->
                when {

                    remoteDataState is DataState.Error -> {
                        emit(remoteDataState)
                    }

                    remoteDataState is DataState.Data -> {
                        remoteDataState.data?.let { remoteNoteList ->
                            val insertedNoteIds = noteRepository.insertNoteList(remoteNoteList)
                            if (insertedNoteIds.isNotEmpty())
                                emit(DataState.Data(localNoteList))
                        }

                    }

                    localNoteList.isNotEmpty() -> {
                        emit(DataState.Data(localNoteList))
                    }

                }
            }
        }.onStart { emit(DataState.Loading(loadingState = LoadingState.Loading)) }
            .distinctUntilChanged()


    /*   @OptIn(FlowPreview::class)
       suspend fun invoke(noteOrder: NoteOrder = NoteOrder.Date(orderType = OrderType.Descending)): Flow<DataState<List<Note>>> {
           val remoteNotesFlow = getRemoteNotesByUserId()
           val localNotesFlow = noteRepository.getNotes()

           return remoteNotesFlow.flatMapConcat { remoteNotesState ->
               localNotesFlow.transform { localNotesState ->
                   when (remoteNotesState) {
                       is DataState.Error -> {
                           emit(DataState.Error(remoteNotesState.errorMessage))
                       }
                       is DataState.Loading -> {
                           emit(DataState.Loading(remoteNotesState.loadingState))
                       }
                       is DataState.Data -> {
                           val noteList =
                               remoteNotesState.data.orEmpty() + localNotesState

                           emit(DataState.Data(data = noteList))
                           emit(DataState.Loading(loadingState = LoadingState.Idle))
                       }
                   }
               }
           }.onStart { emit(DataState.Loading(loadingState = LoadingState.Loading)) }
       }*/


}