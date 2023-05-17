package com.alitafreshi.task_list.di

import com.alitafreshi.data.qualifier.IoDispatcher
import com.alitafreshi.domain.interactors.*
import com.alitafreshi.domain.interactors.remote.GetRemoteNotesByUserId
import com.alitafreshi.domain.repository.NoteRepository
import com.alitafreshi.domain.repository.NoteSynchronizerRepository
import com.alitafreshi.domain.repository.remote.NoteRemoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher


@Module
@InstallIn(ViewModelComponent::class)
object TaskListModule {


    @ViewModelScoped
    @Provides
    fun provideGetNoteByUserId(
        noteRemoteRepository: NoteRemoteRepository,
        noteRepository: NoteRepository,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): GetRemoteNotesByUserId =
        GetRemoteNotesByUserId(
            noteRemoteRepository = noteRemoteRepository,
            noteRepository = noteRepository,
            ioDispatcher = ioDispatcher
        )

    @ViewModelScoped
    @Provides
    fun provideGetNoteUseCase(
        noteSynchronizerRepository: NoteSynchronizerRepository
    ): GetNotesUseCase = GetNotesUseCase(noteSynchronizerRepository = noteSynchronizerRepository)


    @ViewModelScoped
    @Provides
    fun provideDeleteNoteUseCase(noteRepository: NoteRepository): DeleteNoteUseCase =
        DeleteNoteUseCase(noteRepository = noteRepository)

    @ViewModelScoped
    @Provides
    fun provideRestoreDeletedNotesUseCase(noteRepository: NoteRepository): RestoreDeletedNotesUseCase =
        RestoreDeletedNotesUseCase(noteRepository = noteRepository)
}