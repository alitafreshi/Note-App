package com.alitafreshi.data.datasource.local.datastore

import kotlinx.serialization.Serializable

@Serializable
data class ProtoDataStoreObj<T>(
    val savedObj: T? = null
) {
    companion object Factory {

    }
}



