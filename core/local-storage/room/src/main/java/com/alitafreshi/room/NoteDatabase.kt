package com.alitafreshi.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alitafreshi.room_db.task.NoteDao
import com.alitafreshi.room_db.task.model.Note


@Database(
    entities = [Note::class],
    version = 1,
    exportSchema = false
)
abstract class NoteDatabase : RoomDatabase(), NoteAppDatabase {
    abstract override fun noteDao(): NoteDao

}

