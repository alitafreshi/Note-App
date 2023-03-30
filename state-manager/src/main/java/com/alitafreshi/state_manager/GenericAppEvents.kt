package com.alitafreshi.state_manager

import android.net.Uri
import androidx.navigation.NavController
import androidx.navigation.NavOptions

sealed class AppEvents {

    data class UpdateLoadingState(val state: Boolean) : AppEvents()
    data class UpdateErrorState(val state: Boolean) : AppEvents()
    data class ShowSnackBar(val message: String) : AppEvents()
    sealed class Navigation {

        data class Navigate(val deepLink: Uri) : AppEvents()
        object NavigateBack : AppEvents()
        data class NavigateWithNavOptions(
            val navController: NavController,
            val deepLink: Uri,
            val navOptions: NavOptions?
        ) : AppEvents()

    }
}




