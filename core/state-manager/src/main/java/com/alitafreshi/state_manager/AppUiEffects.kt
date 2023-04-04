package com.alitafreshi.state_manager

import android.net.Uri
import androidx.navigation.NavOptions

sealed class AppUiEffects {

    data class UpdateLoadingState(val state: Boolean) : AppUiEffects()
    data class UpdateErrorState(val state: Boolean) : AppUiEffects()
    data class ShowSnackBar(val message: String) : AppUiEffects()
    sealed class Navigation:AppUiEffects() {

        object DetectStartGraph:Navigation()
        data class Navigate(val deepLink: Uri) : Navigation()
        object NavigateBack : Navigation()
        data class NavigateWithNavOptions(
            val deepLink: Uri,
            val navOptions: NavOptions?
        ) : Navigation()

    }
}




