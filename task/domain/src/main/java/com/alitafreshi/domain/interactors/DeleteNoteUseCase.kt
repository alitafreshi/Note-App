package com.alitafreshi.domain.interactors

import com.alitafreshi.domain.model.Note
import com.alitafreshi.domain.repository.NoteRepository

class DeleteNoteUseCase(private val noteRepository: NoteRepository) {

    suspend operator fun invoke(note: Note) = noteRepository.deleteNote(note = note)

}