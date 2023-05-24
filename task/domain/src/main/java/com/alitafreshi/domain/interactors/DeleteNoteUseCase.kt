package com.alitafreshi.domain.interactors

import com.alitafreshi.domain.repository.local.NoteLocalRepository
class DeleteNoteUseCase(private val noteRepository: NoteLocalRepository) {
    suspend operator fun invoke(notes: List<Int>) = noteRepository.updateRemoveStatus(notesId = notes)
}