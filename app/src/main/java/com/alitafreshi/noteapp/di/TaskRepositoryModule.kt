package com.alitafreshi.noteapp.di

import com.alitafreshi.data.datasource.local.room.NoteDatabase
import com.alitafreshi.data.datasource.local.room.repository.NoteRepositoryImpl
import com.alitafreshi.domain.repository.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(ViewModelComponent::class)
@Module
object TaskRepositoryModule {

    @ViewModelScoped
    @Provides
    fun provideNoteRepository(db: NoteDatabase): NoteRepository =
        NoteRepositoryImpl(noteDao = db.noteDao)
}