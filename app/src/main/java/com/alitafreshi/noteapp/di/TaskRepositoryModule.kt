package com.alitafreshi.noteapp.di

import com.alitafreshi.data.datasource.local.repository.NoteRepositoryImpl
import com.alitafreshi.data.datasource.remote.repository.NoteRemoteRepositoryImpl
import com.alitafreshi.domain.repository.NoteRepository
import com.alitafreshi.domain.repository.remote.NoteRemoteRepository
import com.alitafreshi.domain.repository.remote.NoteRemoteService
import com.alitafreshi.room.NoteAppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit

@InstallIn(ViewModelComponent::class)
@Module
object TaskRepositoryModule {

    @ViewModelScoped
    @Provides
    fun provideMyApiService(retrofit: Retrofit): NoteRemoteService {
        return retrofit.create(NoteRemoteService::class.java)
    }

    @ViewModelScoped
    @Provides
    fun provideRemoteNoteRepository(noteRemoteService: NoteRemoteService): NoteRemoteRepository =
        NoteRemoteRepositoryImpl(noteRemoteService = noteRemoteService)

    @ViewModelScoped
    @Provides
    fun provideNoteRepository(db: NoteAppDatabase): NoteRepository =
        NoteRepositoryImpl(noteDao = db.noteDao())
}