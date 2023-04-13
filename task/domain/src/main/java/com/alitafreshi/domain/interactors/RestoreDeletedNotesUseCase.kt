package com.alitafreshi.domain.interactors


import com.alitafreshi.domain.repository.NoteRepository
import com.alitafreshi.room_db.task.model.InvalidNoteException
import com.alitafreshi.room_db.task.model.Note

class RestoreDeletedNotesUseCase(private val noteRepository: NoteRepository) {

    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(notes: List<Note>) {
        val restoredNotes = noteRepository.insertNoteList(notes = notes)
        if (restoredNotes.size != notes.size)
            throw InvalidNoteException("عملیات بازنشانی آیتم های حذف شده وجود ندارد")
    }
}