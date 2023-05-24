package com.alitafreshi.task_add_edit.presentation

import androidx.lifecycle.SavedStateHandle
import com.alitafreshi.components.util.app.BaseViewModel
import com.alitafreshi.components.util.app.Navigation
import com.alitafreshi.domain.interactors.GetNoteByIdUseCase
import com.alitafreshi.domain.interactors.InsertNewNoteUseCase
import com.alitafreshi.room_db.task.model.InvalidNoteException
import com.alitafreshi.room_db.task.model.Note
import com.alitafreshi.state_manager.AppStateManager
import com.alitafreshi.state_manager.AppUiEffects
import com.alitafreshi.task_add_edit.presentation.view_event.AdEditEvents
import com.alitafreshi.task_add_edit.presentation.view_state.AdEditViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import saman.zamani.persiandate.PersianDate
import saman.zamani.persiandate.PersianDateFormat
import javax.inject.Inject


@HiltViewModel
class AddEditViewModel @Inject constructor(
    private val getNoteByIdUseCase: GetNoteByIdUseCase,
    private val insertNewNoteUseCase: InsertNewNoteUseCase,
    private val applicationStateManager: AppStateManager,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<AdEditViewState, AdEditEvents, Unit>() {

    init {
        savedStateHandle.get<Int>("taskId")?.let { taskId ->
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
                        val pdate = PersianDate()
                        val pdformater1 = PersianDateFormat("Y/m/d")

                        insertNewNoteUseCase(

                            //TODO Color should Be completed Later on

                            note = Note(
                                localId = getCurrentViewStateOrNew().noteId,
                                title = getCurrentViewStateOrNew().taskAdEditTitleTextFieldState.text,
                                description = getCurrentViewStateOrNew().taskAdEditDescriptionTextFieldState.text,
                                date = pdformater1.format(pdate)
                            )
                        )
                        applicationStateManager.emitSuspendAppUiEffect(uiEffect = Navigation.NavigateBack)
                    } catch (e: InvalidNoteException) {
                        applicationStateManager.emitSuspendAppUiEffect(
                            uiEffect = AppUiEffects.ShowSnackBar(
                                message = e.message ?: "Unknown Message Cant save note"
                            )
                        )
                    }
                }
            }

            is AdEditEvents.GetTaskById -> {
                handleSuspendEvent {
                    getNoteByIdUseCase(id = event.taskId)?.also { note ->
                        setViewState(
                            viewState = getCurrentViewStateOrNew().copy(
                                noteId = note.localId,
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