package com.alitafreshi.data.datasource.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alitafreshi.domain.model.Note
@Database(
    entities = [Note::class],
    version = 1,
    exportSchema = false
)
abstract class NoteDatabase :RoomDatabase() {
    abstract val noteDao: NoteDao
}