package com.alitafreshi.domain.model

import com.alitafreshi.room_db.task.model.Note
import com.google.gson.annotations.SerializedName

data class NoteDto(
    @SerializedName("noteId")
    val noteId: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("user")
    val user: UserDto = UserDto(userId = 1, phoneNumber = "0926544258279")
)

fun NoteDto.toNote(): Note =
    Note(id = noteId, title = title, description = description, date = date)

