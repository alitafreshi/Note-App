package com.alitafreshi.proto_datastore

import kotlinx.serialization.Serializable

@Serializable
data class AppSettings(
    val introState: Boolean = false
)
