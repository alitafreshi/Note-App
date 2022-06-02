package com.alitafreshi.domain.interactors

import com.alitafreshi.domain.repository.NoteRepository

class GetNoteByIdUseCase(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(id: Int) = noteRepository.getNoteById(id = id)
}