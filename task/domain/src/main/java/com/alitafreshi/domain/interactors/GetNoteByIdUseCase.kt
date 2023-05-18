package com.alitafreshi.domain.interactors

import com.alitafreshi.domain.repository.local.NoteLocalRepository

class GetNoteByIdUseCase(private val noteRepository: NoteLocalRepository) {
    suspend operator fun invoke(id: Int) = noteRepository.getNoteById(id = id)
}