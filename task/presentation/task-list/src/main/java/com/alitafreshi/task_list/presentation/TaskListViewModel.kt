package com.alitafreshi.task_list.presentation

import android.net.Uri
import androidx.lifecycle.viewModelScope
import com.alitafreshi.components.util.app.BaseViewModel
import com.alitafreshi.components.util.app.Navigation
import com.alitafreshi.domain.interactors.DeleteNoteUseCase
import com.alitafreshi.domain.interactors.GetNotesUseCase
import com.alitafreshi.domain.interactors.RestoreDeletedNotesUseCase
import com.alitafreshi.domain.interactors.remote.GetRemoteNotesByUserId
import com.alitafreshi.state_manager.AppStateManager
import com.alitafreshi.task_list.presentation.util.asDataState
import com.alitafreshi.task_list.presentation.util.handleAndCatchGeneralStates
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
                handleSuspendEvent {
                    getNotesUseCase().asDataState()
                        .handleAndCatchGeneralStates(stateManager = applicationStateManager)
                        .onEach { noteList ->

                            setViewState(viewState = getCurrentViewStateOrNew().copy(taskList = noteList))

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
}

