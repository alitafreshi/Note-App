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
    Note(remoteId = noteId, title = title, description = description, date = date)

fun List<NoteDto>.toNoteList(): List<Note> = buildList {
    this@toNoteList.forEach { add(it.toNote()) }
}

fun Note.toNoteDto(): NoteDto = NoteDto(
    noteId = remoteId ?: 0,
    title = title,
    description = description,
    date = date,
    user = UserDto(userId = 10, phoneNumber = "0926544258279")
)

fun List<Note>.toNoteDto(): List<NoteDto> = buildList {
    this@toNoteDto.forEach { add(it.toNoteDto()) }
}

