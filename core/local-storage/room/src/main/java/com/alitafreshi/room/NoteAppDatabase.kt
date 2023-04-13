package com.alitafreshi.room

import com.alitafreshi.room_db.task.NoteDao


interface NoteAppDatabase {
    fun noteDao(): NoteDao
}