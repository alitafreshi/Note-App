package com.alitafreshi.room_db.task.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    @PrimaryKey
    val id: Int? = null,
    val title: String,
    //TODO Later on we should save the whole text object with it styles like samsung notes
    val description: String,
    val date: String,
    val isRemoved: Boolean = false
)

class InvalidNoteException(message: String) : Exception(message)
