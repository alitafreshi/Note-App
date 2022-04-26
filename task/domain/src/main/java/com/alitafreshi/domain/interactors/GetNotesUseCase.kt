package com.alitafreshi.domain.interactors

import com.alitafreshi.domain.model.Note
import com.alitafreshi.domain.repository.NoteRepository
import com.alitafreshi.domain.uti.NoteOrder
import com.alitafreshi.domain.uti.OrderType
import kotlinx.coroutines.flow.Flow

class GetNotesUseCase(private val noteRepository: NoteRepository) {

    operator fun invoke(noteOrder: NoteOrder = NoteOrder.Date(orderType = OrderType.Descending)): Flow<List<Note>> =
        noteRepository.getNotes()

}