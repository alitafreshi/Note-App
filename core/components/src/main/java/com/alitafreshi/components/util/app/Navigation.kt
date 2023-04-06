package com.alitafreshi.components.util.app

import android.net.Uri
import androidx.navigation.NavOptions
import com.alitafreshi.domain.BaseUiEffects

sealed class Navigation : BaseUiEffects {

    object DetectStartGraph : Navigation()
    data class Navigate(val deepLink: Uri) : Navigation()
    object NavigateBack : Navigation()
    data class NavigateWithNavOptions(
        val deepLink: Uri,
        val navOptions: NavOptions?
    ) : Navigation()
}