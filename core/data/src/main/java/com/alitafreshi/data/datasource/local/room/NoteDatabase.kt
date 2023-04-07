package com.alitafreshi.data.datasource.local.room

import com.alitafreshi.data.datasource.local.room.dao.NoteDao

interface NoteDatabase {
    fun noteDao():NoteDao
}