package com.alitafreshi.noteapp.presentation.app

sealed class AppEvents {
    data class UpdateIntroState(val introState: Boolean) : AppEvents()
}
