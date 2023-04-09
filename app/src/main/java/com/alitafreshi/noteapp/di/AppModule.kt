package com.alitafreshi.noteapp.di

import android.content.Context
import androidx.room.Room
import com.alitafreshi.constance.Constance.APP_ROOM_DATABASE
import com.alitafreshi.room.NoteDatabase
import com.alitafreshi.data.qualifier.ApplicationScope
import com.alitafreshi.data.qualifier.IoDispatcher
import com.alitafreshi.room.NoteAppDatabase
import com.alitafreshi.state_manager.AppStateManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideApplicationContext(@ApplicationContext app: Context): BaseApplication =
        app as BaseApplication

    @Singleton
    @Provides
    fun provideAppRoomDatabase(app: BaseApplication): NoteAppDatabase =
        Room.databaseBuilder(app, NoteDatabase::class.java, APP_ROOM_DATABASE).build()


    @Singleton
    @ApplicationScope
    @Provides
    fun providesCoroutineScope(@IoDispatcher ioDispatcher: CoroutineDispatcher): CoroutineScope {
        return CoroutineScope(SupervisorJob() + ioDispatcher)
    }

    @Singleton
    @Provides
    fun provideApplicationStateManager(@ApplicationScope applicationScope: CoroutineScope): AppStateManager =
        AppStateManager(applicationScope = applicationScope)
}