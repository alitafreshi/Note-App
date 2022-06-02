package com.alitafreshi.domain.interactors

import com.alitafreshi.domain.model.InvalidNoteException
import com.alitafreshi.domain.model.Note
import com.alitafreshi.domain.repository.NoteRepository
import kotlin.jvm.Throws

class InsertNewNoteUseCase(private val noteRepository: NoteRepository) {

    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        val insertedNoteId = noteRepository.insertNote(note = note)
        if (insertedNoteId < 0)
            throw InvalidNoteException("عملیات افزودن آیتم مورد نظر به مشکل خورد")
    }

}