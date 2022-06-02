package com.alitafreshi.domain.interactors

data class NoteUseCases(
    val getNotesUseCase: GetNotesUseCase,
    val getNoteByIdUseCase: GetNoteByIdUseCase,
    val deleteNoteUseCase: DeleteNoteUseCase,
    val insertNewNoteUseCase: InsertNewNoteUseCase,
    val restoreDeletedNotesUseCase: RestoreDeletedNotesUseCase
)
