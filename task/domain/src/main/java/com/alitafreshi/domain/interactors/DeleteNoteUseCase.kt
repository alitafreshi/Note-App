package com.alitafreshi.domain.interactors

import com.alitafreshi.domain.repository.NoteRepository
import com.alitafreshi.room_db.task.model.Note

class DeleteNoteUseCase(private val noteRepository: NoteRepository) {

    suspend operator fun invoke(notes: List<Note>) = noteRepository.deleteNotes(notes = notes)

}