package com.alitafreshi.noteapp.di

import com.alitafreshi.data.datasource.local.room.repository.NoteRepositoryImpl
import com.alitafreshi.domain.repository.NoteRepository
import com.alitafreshi.room.NoteAppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
object TaskRepositoryModule {

    @ViewModelScoped
    @Provides
    fun provideNoteRepository(db: NoteAppDatabase): NoteRepository =
        NoteRepositoryImpl(noteDao = db.noteDao())
}