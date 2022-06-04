package com.alitafreshi.noteapp.presentation.navigation.destinations.task.task_ad_edit

import androidx.lifecycle.SavedStateHandle
import com.alitafreshi.components.util.app.BaseViewModel
import com.alitafreshi.domain.interactors.NoteUseCases
import com.alitafreshi.domain.model.InvalidNoteException
import com.alitafreshi.domain.model.Note
import com.alitafreshi.noteapp.presentation.destinations.TaskAdEditDestinationDestination
import com.alitafreshi.task_add_edit.UiEvents
import com.alitafreshi.task_add_edit.view_event.AdEditEvents
import com.alitafreshi.task_add_edit.view_state.AdEditViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.tafreshiali.ayan_core.util.UIComponent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

@HiltViewModel
class AdEditViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<AdEditViewState, AdEditEvents, UIComponent>() {

    private val _eventFlow = MutableSharedFlow<UiEvents>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        //TODO Update The Note Id With Navigation Arguments And SavedStateHandle
        TaskAdEditDestinationDestination.argsFrom(savedStateHandle).taskId?.let { taskId ->

            if (taskId != -1) {
                onTriggerEvent(event = AdEditEvents.GetTaskById(taskId = taskId))
            }
        }
    }

    override fun initNewViewState(): AdEditViewState = AdEditViewState()


    override fun onTriggerEvent(event: AdEditEvents) {
        when (event) {

            is AdEditEvents.UpdateTitleContent -> {
                setViewState(
                    viewState = getCurrentViewStateOrNew().copy(
                        taskAdEditTitleTextFieldState = getCurrentViewStateOrNew().taskAdEditTitleTextFieldState.copy(
                            text = event.value
                        )
                    )
                )
            }

            is AdEditEvents.ChangeTitleFocusState -> {
                setViewState(
                    viewState = getCurrentViewStateOrNew().copy(
                        taskAdEditTitleTextFieldState = getCurrentViewStateOrNew().taskAdEditTitleTextFieldState.copy(
                            isHintEnabled = !event.focusState.isFocused && getCurrentViewStateOrNew().taskAdEditTitleTextFieldState.text.isBlank()
                        )
                    )
                )
            }

            is AdEditEvents.UpdateDescriptionContent -> {
                setViewState(
                    viewState = getCurrentViewStateOrNew().copy(
                        taskAdEditDescriptionTextFieldState = getCurrentViewStateOrNew().taskAdEditDescriptionTextFieldState.copy(
                            text = event.value
                        )
                    )
                )
            }

            is AdEditEvents.ChangeDescriptionState -> {
                setViewState(
                    viewState = getCurrentViewStateOrNew().copy(
                        taskAdEditDescriptionTextFieldState = getCurrentViewStateOrNew().taskAdEditDescriptionTextFieldState.copy(
                            isHintEnabled = !event.focusState.isFocused && getCurrentViewStateOrNew().taskAdEditDescriptionTextFieldState.text.isBlank()
                        )
                    )
                )
            }

            is AdEditEvents.SaveNote -> {
                handleSuspendEvent {
                    try {
                        noteUseCases.insertNewNoteUseCase.invoke(
                            //TODO Date And Color should Be completed Later on
                            note = Note(
                                id = getCurrentViewStateOrNew().noteId,
                                title = getCurrentViewStateOrNew().taskAdEditTitleTextFieldState.text,
                                description = getCurrentViewStateOrNew().taskAdEditDescriptionTextFieldState.text,
                                date = "",
                                color = 0

                            )
                        )
                        _eventFlow.emit(UiEvents.SaveNote)
                    } catch (e: InvalidNoteException) {
                        _eventFlow.emit(
                            UiEvents.ShowSnackBar(
                                message = e.message ?: "Unknown Message Cant save note"
                            )
                        )
                    }
                }
            }

            is AdEditEvents.GetTaskById -> {
                handleSuspendEvent {
                    noteUseCases.getNoteByIdUseCase(id = event.taskId)?.also { note ->
                        setViewState(
                            viewState = getCurrentViewStateOrNew().copy(
                                noteId = note.id,
                                taskAdEditTitleTextFieldState = getCurrentViewStateOrNew().taskAdEditTitleTextFieldState.copy(
                                    text = note.title,
                                    isHintEnabled = false
                                ),
                                taskAdEditDescriptionTextFieldState = getCurrentViewStateOrNew().taskAdEditDescriptionTextFieldState.copy(
                                    text = note.description,
                                    isHintEnabled = false
                                )
                            )
                        )

                    }
                }
            }
        }
    }

}