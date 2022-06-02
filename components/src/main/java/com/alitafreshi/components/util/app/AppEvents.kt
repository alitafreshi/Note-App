package com.alitafreshi.components.util.app

sealed class AppEvents {
    data class UpdateIntroState(val introState: Boolean) : AppEvents()
}
