package com.alitafreshi.data.datasource.local.datastore

import androidx.datastore.core.Serializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

@Suppress("BlockingMethodInNonBlockingContext")
class GenericProtoDataStoreSerializer<T>(
    private val serializer: KSerializer<T>,
    override val defaultValue: T
) : Serializer<T> {

    override suspend fun readFrom(input: InputStream): T = try {
        Json.decodeFromString(serializer, input.readBytes().decodeToString())
    } catch (e: SerializationException) {
        e.printStackTrace()
        defaultValue
    }

    override suspend fun writeTo(t: T, output: OutputStream) {
        output.write(Json.encodeToString(serializer, t).encodeToByteArray())
    }
}





