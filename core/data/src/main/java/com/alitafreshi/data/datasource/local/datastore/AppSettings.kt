package com.alitafreshi.data.datasource.local.datastore

import kotlinx.serialization.Serializable

@Serializable
data class AppSettings(
    val introState: Boolean = false
)
