package com.alitafreshi.task_list.presentation

import android.net.Uri
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.alitafreshi.components.util.app.BaseViewModel
import com.alitafreshi.components.util.app.Navigation
import com.alitafreshi.domain.DataState
import com.alitafreshi.domain.LoadingState
import com.alitafreshi.domain.interactors.DeleteNoteUseCase
import com.alitafreshi.domain.interactors.GetNotesUseCase
import com.alitafreshi.domain.interactors.RestoreDeletedNotesUseCase
import com.alitafreshi.room_db.task.model.InvalidNoteException
import com.alitafreshi.room_db.task.model.Note
import com.alitafreshi.state_manager.AppStateManager
import com.alitafreshi.task_list.presentation.util.handleAndCatchGeneralStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel @Inject constructor(
    private val getNotesUseCase: GetNotesUseCase,
    private val restoreDeletedNotesUseCase: RestoreDeletedNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val applicationStateManager: AppStateManager
) : BaseViewModel<TaskListViewState, TaskListEvents, Unit>() {


    init {
        onTriggerEvent(event = TaskListEvents.RetrieveNoteList)
    }

    override fun initNewViewState(): TaskListViewState = TaskListViewState()


    override fun onTriggerEvent(event: TaskListEvents) {
        when (event) {

            is TaskListEvents.RetrieveNoteList -> {
                handleSuspendEvent {
                    getNotesUseCase().handleAndCatchGeneralStates(stateManager = applicationStateManager)
                        .onEach { noteList ->
                            Log.d("RetrieveNoteList", "NoteList is $noteList")

                        }.launchIn(viewModelScope)
                }
            }

            is TaskListEvents.DeleteNotes -> handleSuspendEvent {
                deleteNoteUseCase(
                    notes = event.deletedNotes
                )
                setViewState(viewState = getCurrentViewStateOrNew().copy(selectedTaskList = emptyList()))
            }

            is TaskListEvents.RestoreNote -> handleSuspendEvent {
                restoreDeletedNotesUseCase(
                    notes = event.restoredNotes
                )
            }

            is TaskListEvents.AddToSelectionList -> {
                val selectedTasksList = getCurrentViewStateOrNew().selectedTaskList.toMutableList()

                if (getCurrentViewStateOrNew().selectedTaskList.isNotEmpty() && getCurrentViewStateOrNew().selectedTaskList.contains(
                        event.note
                    )
                ) {
                    selectedTasksList.remove(event.note)
                } else {
                    selectedTasksList.add(event.note)
                }
                setViewState(
                    viewState =
                    getCurrentViewStateOrNew().copy(
                        selectedTaskList = selectedTasksList
                    )
                )
            }
            is TaskListEvents.NavigateToNoteAddEditFragment -> applicationStateManager.emitSuspendAppUiEffect(
                uiEffect = Navigation.Navigate(
                    deepLink = Uri.parse("https://tafreshiali.ir/tasks/${event.noteId}")
                )
            )
        }
    }

    private fun flowSample() {
        viewModelScope.launch {

            val remoteResponseFlow = flow<DataState<List<Note>>> {
                delay(1000L)
                emit(DataState.Loading(loadingState = LoadingState.Loading))
                delay(500L)
                emit(DataState.Loading(loadingState = LoadingState.Idle))
                delay(500L)
                throw InvalidNoteException("SomeThing Went Wrong")

                /*     emit(
                           DataState.Data(
                               data = listOf(
                                   ("RemoteNote1"),
                                   ("RemoteNote2"),
                                   ("RemoteNote3"),
                                   ("RemoteNote4"),
                                   ("RemoteNote5"),
                                   ("RemoteNote6")
                               )
                           )
                       )*/

            }

            val localStorageFlow = flow {
                emit(DataState.Loading(loadingState = LoadingState.Loading))
                delay(4000L)
                emit(DataState.Loading(loadingState = LoadingState.Idle))
                emit(
                    DataState.Data(
                        listOf(
                            ("localNote1"),
                            ("localNote2"),
                            ("localNote3"),
                            ("localNote4"),
                            ("localNote5"),
                            ("localNote6")
                        )
                    )

                )
            }

            localStorageFlow.flatMapConcat { localDataState ->

                remoteResponseFlow.transform { remoteDataState ->

                    when {
                        remoteDataState is DataState.Error || localDataState is DataState.Error -> {
                            emit(remoteDataState)
                        }

                        remoteDataState is DataState.Data && localDataState is DataState.Data -> {
                            emit(DataState.Loading(loadingState = LoadingState.Idle))
                            val combinedData =
                                (remoteDataState as? DataState.Data)?.data ?: emptyList()

                            emit(
                                DataState.Data(data = localDataState.data?.plus(combinedData))
                            )
                        }
                    }
                }
            }.onStart { emit(DataState.Loading(loadingState = LoadingState.Loading)) }
                .distinctUntilChanged()
                .catch {
                    emit(
                        DataState.Error(
                            errorMessage = it.localizedMessage ?: "Unknown error occurred"
                        )
                    )
                }
                .collect { value ->
                    Log.d("finalResultFlow", "dataState is $value")
                }
        }
    }
}

