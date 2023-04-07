package com.alitafreshi.task_list.di

import com.alitafreshi.data.datasource.local.room.NoteDatabase
import com.alitafreshi.data.datasource.local.room.repository.NoteRepositoryImpl
import com.alitafreshi.domain.interactors.*
import com.alitafreshi.domain.repository.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Named


@Module
@InstallIn(ViewModelComponent::class)
object TaskListModule {

    @ViewModelScoped
    @Provides
    @Named("TaskListRepository")
    fun provideNoteRepository(db: NoteDatabase): NoteRepository =
        NoteRepositoryImpl(noteDao = db.noteDao)

    @ViewModelScoped
    @Provides
    fun provideGetNoteUseCase(noteRepository: NoteRepository): GetNotesUseCase =
        GetNotesUseCase(noteRepository = noteRepository)


    @ViewModelScoped
    @Provides
    fun provideDeleteNoteUseCase(noteRepository: NoteRepository): DeleteNoteUseCase =
        DeleteNoteUseCase(noteRepository = noteRepository)

    @ViewModelScoped
    @Provides
    fun provideRestoreDeletedNotesUseCase(noteRepository: NoteRepository): RestoreDeletedNotesUseCase =
        RestoreDeletedNotesUseCase(noteRepository = noteRepository)
}