package com.alitafreshi.task_add_edit

sealed class UiEvents {
    data class ShowSnackBar(val message: String) : UiEvents()
    object SaveNote : UiEvents()

}
