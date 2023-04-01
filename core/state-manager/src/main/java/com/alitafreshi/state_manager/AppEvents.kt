package com.alitafreshi.state_manager

import android.net.Uri
import androidx.navigation.NavOptions

sealed class AppEvents {

    data class UpdateLoadingState(val state: Boolean) : AppEvents()
    data class UpdateErrorState(val state: Boolean) : AppEvents()
    data class ShowSnackBar(val message: String) : AppEvents()
    sealed class Navigation:AppEvents() {

        object DetectStartGraph:Navigation()
        data class Navigate(val deepLink: Uri) : Navigation()
        object NavigateBack : Navigation()
        data class NavigateWithNavOptions(
            val deepLink: Uri,
            val navOptions: NavOptions?
        ) : Navigation()

    }
}




