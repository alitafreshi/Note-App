package com.alitafreshi.noteapp.di

import com.alitafreshi.constance.Constance.APP_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.gson.*
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RemoteModule {

    @Singleton
    @Provides
    fun provideKtorClient(): HttpClient = HttpClient(OkHttp) {
        expectSuccess = true

        /*install(Logging) {
            logger = Logger.ANDROID
            level = LogLevel.ALL
            filter { request ->
                request.url.host.contains(APP_BASE_URL)
            }

        }*/

        install(ContentNegotiation) {
            gson(block = {
                setPrettyPrinting()
                enableComplexMapKeySerialization()
                disableHtmlEscaping()
            })
        }

        install(HttpTimeout) {
            requestTimeoutMillis = 2000
        }

        defaultRequest {
            url(APP_BASE_URL)

        }

        engine {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            addInterceptor(loggingInterceptor)
            config {
                followRedirects(true)
            }
        }
    }
}

