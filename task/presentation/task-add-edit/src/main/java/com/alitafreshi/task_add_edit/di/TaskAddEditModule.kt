package com.alitafreshi.task_add_edit.di

import com.alitafreshi.domain.interactors.GetNoteByIdUseCase
import com.alitafreshi.domain.interactors.InsertNewNoteUseCase
import com.alitafreshi.domain.repository.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
object TaskAddEditModule {


    @ViewModelScoped
    @Provides
    fun provideGetNoteUseCaseById(noteRepository: NoteRepository): GetNoteByIdUseCase =
        GetNoteByIdUseCase(noteRepository = noteRepository)


    @ViewModelScoped
    @Provides
    fun provideInsertNewNoteUseCase(noteRepository: NoteRepository): InsertNewNoteUseCase =
        InsertNewNoteUseCase(noteRepository = noteRepository)

}