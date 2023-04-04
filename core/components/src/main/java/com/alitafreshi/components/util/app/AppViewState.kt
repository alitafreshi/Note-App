package com.alitafreshi.components.util.app

import com.alitafreshi.domain.LoadingState


data class AppViewState(
    val theme: String = "",
    val introState: Boolean = false,
    val loadingState: LoadingState = LoadingState.Idle
)
