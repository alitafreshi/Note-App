package com.alitafreshi.noteapp.presentation.app

import ir.tafreshiali.ayan_core.util.BottomSheetState

data class AppViewState(
    val theme: String = "",
    val introState: Boolean = false,
    val loadingState: BottomSheetState = BottomSheetState.Idle
)
