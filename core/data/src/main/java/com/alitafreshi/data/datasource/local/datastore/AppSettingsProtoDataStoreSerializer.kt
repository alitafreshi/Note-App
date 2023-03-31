package com.alitafreshi.data.datasource.local.datastore

import androidx.datastore.core.Serializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

@Suppress("BlockingMethodInNonBlockingContext")
object AppSettingsProtoDataStoreSerializer : Serializer<ProtoDataStoreObj<AppSettings>> {
    override val defaultValue: ProtoDataStoreObj<AppSettings>
        get() = ProtoDataStoreObj(savedObj = AppSettings())

    override suspend fun readFrom(input: InputStream): ProtoDataStoreObj<AppSettings> = try {
        Json.decodeFromString(
            deserializer = ProtoDataStoreObj.serializer(typeSerial0 = AppSettings.serializer()),
            string = input.readBytes().decodeToString()
        )
    } catch (e: SerializationException) {
        e.printStackTrace()
        defaultValue
    }

    override suspend fun writeTo(t: ProtoDataStoreObj<AppSettings>, output: OutputStream) {
        output.write(
            Json.encodeToString(
                serializer = ProtoDataStoreObj.serializer(typeSerial0 = AppSettings.serializer()),
                value = t
            )
                .encodeToByteArray()
        )
    }
}