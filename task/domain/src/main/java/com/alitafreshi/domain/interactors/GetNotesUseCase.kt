package com.alitafreshi.domain.interactors

import com.alitafreshi.domain.repository.NoteSynchronizerRepository
import com.alitafreshi.domain.util.NoteOrder
import com.alitafreshi.domain.util.OrderType
import com.alitafreshi.room_db.task.model.Note
import kotlinx.coroutines.flow.Flow

class GetNotesUseCase(
    private val noteSynchronizerRepository: NoteSynchronizerRepository
) {
     suspend operator fun invoke(noteOrder: NoteOrder = NoteOrder.Date(orderType = OrderType.Descending)): Flow<List<Note>> =
        noteSynchronizerRepository.getSyncedNoteList()
}