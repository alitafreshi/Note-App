package com.alitafreshi.task_list.presentation

import android.net.Uri
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.alitafreshi.components.util.app.BaseViewModel
import com.alitafreshi.domain.DataState
import com.alitafreshi.domain.interactors.DeleteNoteUseCase
import com.alitafreshi.domain.interactors.GetNotesUseCase
import com.alitafreshi.domain.interactors.RestoreDeletedNotesUseCase
import com.alitafreshi.state_manager.AppUiEffects
import com.alitafreshi.state_manager.AppStateManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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
                getNotesUseCase().onEach { dataState ->
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
                }.launchIn(viewModelScope)
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
                uiEffect = AppUiEffects.Navigation.Navigate(
                    deepLink = Uri.parse("https://tafreshiali.ir/tasks/${event.noteId}")
                )
            )
        }
    }
}

