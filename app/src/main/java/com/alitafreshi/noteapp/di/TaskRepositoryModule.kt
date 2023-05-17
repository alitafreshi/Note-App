package com.alitafreshi.noteapp.di

import com.alitafreshi.data.datasource.NoteSynchronizerRepositoryImpl
import com.alitafreshi.data.datasource.local.repository.NoteRepositoryImpl
import com.alitafreshi.data.datasource.remote.repository.NoteRemoteRepositoryImpl
import com.alitafreshi.data.qualifier.IoDispatcher
import com.alitafreshi.domain.repository.NoteRepository
import com.alitafreshi.domain.repository.NoteSynchronizerRepository
import com.alitafreshi.domain.repository.remote.NoteRemoteRepository
import com.alitafreshi.room.NoteAppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import io.ktor.client.*
import kotlinx.coroutines.CoroutineDispatcher

@InstallIn(ViewModelComponent::class)
@Module
object TaskRepositoryModule {

    @ViewModelScoped
    @Provides
    fun provideRemoteNoteRepository(httpClient: HttpClient): NoteRemoteRepository =
        NoteRemoteRepositoryImpl(httpClient = httpClient)

    @ViewModelScoped
    @Provides
    fun provideNoteRepository(db: NoteAppDatabase): NoteRepository =
        NoteRepositoryImpl(noteDao = db.noteDao())

    @ViewModelScoped
    @Provides
    fun provideNoteSynchronizerRepository(
        noteRepository: NoteRepository,
        noteRemoteRepository: NoteRemoteRepository,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): NoteSynchronizerRepository =
        NoteSynchronizerRepositoryImpl(
            noteRepository = noteRepository,
            noteRemoteRepository = noteRemoteRepository,
            ioDispatcher = ioDispatcher
        )
}