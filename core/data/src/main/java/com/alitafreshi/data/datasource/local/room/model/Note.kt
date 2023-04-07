package com.alitafreshi.data.datasource.local.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.lang.Exception

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String,
    //TODO Later on we should save the whole text object with it styles like samsung notes
    val description: String,
    val date: String,
    val color: Int
)

class InvalidNoteException(message: String) : Exception(message)
