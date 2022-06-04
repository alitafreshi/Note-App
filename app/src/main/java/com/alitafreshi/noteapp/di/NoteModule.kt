package com.alitafreshi.noteapp.di

import com.alitafreshi.data.datasource.local.room.NoteDatabase
import com.alitafreshi.data.datasource.local.room.repository.NoteRepositoryImpl
import com.alitafreshi.domain.interactors.*
import com.alitafreshi.domain.repository.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NoteModule {

    @Singleton
    @Provides
    fun provideNoteRepository(db: NoteDatabase): NoteRepository =
        NoteRepositoryImpl(noteDao = db.noteDao)

    @Singleton
    @Provides
    fun provideNoteUseCases(noteRepository: NoteRepository): NoteUseCases =
        NoteUseCases(
            getNotesUseCase = GetNotesUseCase(noteRepository = noteRepository),
            getNoteByIdUseCase = GetNoteByIdUseCase(noteRepository = noteRepository),
            deleteNoteUseCase = DeleteNoteUseCase(noteRepository = noteRepository),
            insertNewNoteUseCase = InsertNewNoteUseCase(noteRepository = noteRepository),
            restoreDeletedNotesUseCase = RestoreDeletedNotesUseCase(noteRepository = noteRepository)
        )
}