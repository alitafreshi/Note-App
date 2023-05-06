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
import com.alitafreshi.state_manager.AppStateManager
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
                viewModelScope.launch {

                    val remoteResponseFlow = flow {
                        delay(2000L)
                        emit(DataState.Loading(loadingState = LoadingState.Loading))
                        delay(1000L)
                        emit(DataState.Loading(loadingState = LoadingState.Idle))
                        delay(3000L)
                        emit(
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
                        )

                    }

                    val localStorageFlow = flow {
                        emit(DataState.Loading(loadingState = LoadingState.Loading))
                        delay(1000L)
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
                        .distinctUntilChanged().collect { value ->
                            Log.d("remoteResponseFlow", "dataState is $value")
                        }
                }

                /*getNotesUseCase().onEach { dataState ->
                    when (dataState) {
                        is DataState.Data -> dataState.data?.let { noteList ->
                            setViewState(viewState = getCurrentViewStateOrNew().copy(taskList = noteList))
                        }

                        is DataState.Error -> Log.d(
                            "TaskListViewModel",
                            dataState.errorMessage
                        )

                        is DataState.Loading -> setViewState(
                            viewState = getCurrentViewStateOrNew().copy(
                                loadingState = dataState.loadingState
                            )
                        )
                    }
                }.launchIn(viewModelScope)*/
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
}

