package com.alitafreshi.domain.model

import androidx.annotation.Keep
import com.alitafreshi.room_db.task.model.Note
import com.google.gson.annotations.SerializedName

@Keep
data class NoteDto(
    @SerializedName("date")
    val date: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("noteId")
    val noteId: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("user")
    val user: UserDto
)

fun NoteDto.toNote(): Note =
    Note(id = noteId, title = title, description = description, date = date)

