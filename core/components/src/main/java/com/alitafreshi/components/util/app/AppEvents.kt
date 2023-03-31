package com.alitafreshi.components.util.app

import ir.tafreshiali.ayan_core.util.BottomSheetState

sealed class AppEvents {
    data class UpdateLoadingState(val bottomSheetState: BottomSheetState) : AppEvents()
    data class UpdateIntroState(val introState: Boolean) : AppEvents()
}
