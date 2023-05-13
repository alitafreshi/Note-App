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

        noteRepository.getNotes()
            .combine(getRemoteNotesByUserId()) { localNoteList, remoteDataState ->

                when {
                    remoteDataState is DataState.Error -> {
                        remoteDataState
                    }

                    remoteDataState is DataState.Data -> {
                        remoteDataState.data?.let { remoteNoteList ->
                            val insertedNoteIds = noteRepository.insertNoteList(remoteNoteList)
                            if (insertedNoteIds.isNotEmpty()) {
                                println("Emitted Remote Notes Second")
                                DataState.Data(remoteNoteList)
                            } else {
                                DataState.Data(localNoteList)
                            }
                        } ?: DataState.Data(localNoteList)
                    }
                    else -> {
                        println("Emitted Local Notes First")
                        DataState.Data(localNoteList)
                    }
                }
            }.onStart { emit(DataState.Loading(loadingState = LoadingState.Loading)) }
            .distinctUntilChanged()


/*    @OptIn(FlowPreview::class)
    suspend operator fun invoke(noteOrder: NoteOrder = NoteOrder.Date(orderType = OrderType.Descending)): Flow<DataState<List<Note>>> =

        noteRepository.getNotes().flatMapConcat { localNoteList ->
            getRemoteNotesByUserId().transform { remoteDataState ->
                when {

                    remoteDataState is DataState.Error -> {
                        emit(remoteDataState)
                    }

                    remoteDataState is DataState.Data -> {
                        remoteDataState.data?.let { remoteNoteList ->
                            emit(DataState.Data(remoteNoteList))
                            val insertedNoteIds = noteRepository.insertNoteList(remoteNoteList)
                            if (insertedNoteIds.isNotEmpty()){
                                println("Emitted Remote Notes Second")
                                emit(DataState.Data(remoteNoteList))
                            }
                        }

                    }

                    localNoteList.isNotEmpty() -> {
                        println("Emitted Local Notes First")
                        emit(DataState.Data(localNoteList))
                    }
                }
            }
        }.onStart { emit(DataState.Loading(loadingState = LoadingState.Loading)) }
            .distinctUntilChanged()*/

}