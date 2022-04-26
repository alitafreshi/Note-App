package com.alitafreshi.noteapp.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import androidx.room.Room
import com.alitafreshi.constance.Constance.APP_PROTO_DATASTORE
import com.alitafreshi.constance.Constance.APP_ROOM_DATABASE
import com.alitafreshi.data.datasource.local.datastore.*
import com.alitafreshi.data.datasource.local.room.NoteDatabase
import com.alitafreshi.data.qualifier.IoDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideApplicationContext(@ApplicationContext app: Context): BaseApplication =
        app as BaseApplication

    /** providing app data store for storing values followed by google best practices
     * in this way our app store manage repository does not depend on context
     * @param app
     * for more information see official Docs  =
     * "https://www.youtube.com/watch?v=9ws-cJzlJkU&list=PLWz5rJ2EKKc8to3Ere-ePuco69yBUmQ9C"
     * "https://developer.android.com/topic/libraries/architecture/datastore"
     * "https://developer.android.com/topic/libraries/architecture/datastore?gclid=CjwKCAjwloCSBhAeEiwA3hVo_euHWgRUZszyHhzA_LWk18W5OJVTRweQsoclu3EZ-DF2pgGyVzUyTRoCXuUQAvD_BwE&gclsrc=aw.ds"
     * and an article series about jetpack datastore in medium =
     * "https://medium.com/androiddevelopers/introduction-to-jetpack-datastore-3dc8d74139e7"*/
    /* @Singleton
     @Provides
     fun provideApplicationDataStore(app: BaseApplication): DataStore<Preferences> =
         PreferenceDataStoreFactory.create(
             corruptionHandler = ReplaceFileCorruptionHandler(
                 produceNewData = { emptyPreferences() }
             ),
             migrations = listOf(
                 SharedPreferencesMigration(
                     context = app,
                     sharedPreferencesName = Constance.APP_DATABASE
                 )
             ),
             produceFile = { app.preferencesDataStoreFile(name = Constance.APP_DATABASE) }
         )*/


    @Singleton
    @Provides
    fun provideApplicationProtoDataStore(app: BaseApplication): DataStore<ProtoDataStoreObj<AppSettings>> =
        DataStoreFactory.create(
            serializer = AppSettingsProtoDataStoreSerializer,
            produceFile = { app.dataStoreFile(APP_PROTO_DATASTORE) },
            corruptionHandler = null,
        )


    @Singleton
    @Provides
    fun provideAppProtoDataStore(
        dataStore: DataStore<ProtoDataStoreObj<AppSettings>>,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): AppProtoDataStore<AppSettings> =
        AppProtoDataStoreImpl(dataStore = dataStore, ioDispatcher = ioDispatcher)


    @Singleton
    @Provides
    fun provideAppRoomDatabase(app: BaseApplication): NoteDatabase =
        Room.databaseBuilder(app, NoteDatabase::class.java, APP_ROOM_DATABASE).build()


}