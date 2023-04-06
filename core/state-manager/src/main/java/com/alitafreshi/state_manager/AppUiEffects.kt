package com.alitafreshi.state_manager

import com.alitafreshi.domain.BaseUiEffects


sealed class AppUiEffects: BaseUiEffects {
    data class UpdateLoadingState(val state: Boolean) : AppUiEffects()
    data class UpdateErrorState(val state: Boolean) : AppUiEffects()
    data class ShowSnackBar(val message: String) : AppUiEffects()
}




