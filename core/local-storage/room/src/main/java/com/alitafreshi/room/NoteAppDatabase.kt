package com.alitafreshi.room

import com.alitafreshi.data.datasource.local.room.NoteDao

interface NoteAppDatabase {
    fun noteDao(): NoteDao
}