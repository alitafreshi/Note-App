package com.alitafreshi.task_list.di

import com.alitafreshi.domain.interactors.*
import com.alitafreshi.domain.interactors.remote.GetRemoteNotesByUserId
import com.alitafreshi.domain.repository.NoteRepository
import com.alitafreshi.domain.repository.remote.NoteRemoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
object TaskListModule {


    @ViewModelScoped
    @Provides
    fun provideGetNoteByUserId(noteRemoteRepository: NoteRemoteRepository): GetRemoteNotesByUserId =
        GetRemoteNotesByUserId(noteRemoteRepository = noteRemoteRepository)

    @ViewModelScoped
    @Provides
    fun provideGetNoteUseCase(
        noteRepository: NoteRepository,
        getRemoteNotesByUserId: GetRemoteNotesByUserId
    ): GetNotesUseCase =
        GetNotesUseCase(
            noteRepository = noteRepository,
            getRemoteNotesByUserId = getRemoteNotesByUserId
        )


    @ViewModelScoped
    @Provides
    fun provideDeleteNoteUseCase(noteRepository: NoteRepository): DeleteNoteUseCase =
        DeleteNoteUseCase(noteRepository = noteRepository)

    @ViewModelScoped
    @Provides
    fun provideRestoreDeletedNotesUseCase(noteRepository: NoteRepository): RestoreDeletedNotesUseCase =
        RestoreDeletedNotesUseCase(noteRepository = noteRepository)
}