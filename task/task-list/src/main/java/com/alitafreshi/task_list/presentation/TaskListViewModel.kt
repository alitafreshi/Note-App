package com.alitafreshi.task_list.presentation

import androidx.lifecycle.viewModelScope
import com.alitafreshi.components.util.app.BaseViewModel
import com.alitafreshi.domain.interactors.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.tafreshiali.ayan_core.util.UIComponent
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : BaseViewModel<TaskListViewState, TaskListEvents, UIComponent>() {


    init {
        onTriggerEvent(event = TaskListEvents.RetrieveNoteList)
    }

    override fun initNewViewState(): TaskListViewState = TaskListViewState()

    override fun onTriggerEvent(event: TaskListEvents) {
        when (event) {

            is TaskListEvents.RetrieveNoteList -> {
                noteUseCases.getNotesUseCase().onEach {
                    setViewState(viewState = getCurrentViewStateOrNew().copy(taskList = it))
                }.launchIn(viewModelScope)
            }

            is TaskListEvents.DeleteNotes -> handleSuspendEvent {
                noteUseCases.deleteNoteUseCase(
                    notes = event.deletedNotes
                )
            }

            is TaskListEvents.RestoreNote -> handleSuspendEvent {
                noteUseCases.restoreDeletedNotesUseCase(
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
        }
    }
}

