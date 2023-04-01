package com.alitafreshi.domain.interactors

import com.alitafreshi.domain.model.InvalidNoteException
import com.alitafreshi.domain.model.Note
import com.alitafreshi.domain.repository.NoteRepository
import kotlin.jvm.Throws

class InsertNewNoteUseCase(private val noteRepository: NoteRepository) {

    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if (note.title.isEmpty() || note.description.isEmpty())
            throw InvalidNoteException("لطفا اطلاعات یادداشت رو کامل وارد کنید")

        try {
            noteRepository.insertNote(note = note)
        } catch (e: Exception) {
            throw InvalidNoteException("عملیات افزودن آیتم مورد نظر به مشکل خورد")
        }
    }
}