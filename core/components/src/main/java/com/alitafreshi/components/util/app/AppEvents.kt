package com.alitafreshi.components.util.app

import com.alitafreshi.domain.LoadingState


sealed class AppEvents {
    data class UpdateLoadingState(val loadingState: LoadingState) : AppEvents()
    data class UpdateIntroState(val introState: Boolean) : AppEvents()
}
