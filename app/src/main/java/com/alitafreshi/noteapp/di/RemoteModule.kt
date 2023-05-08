package com.alitafreshi.noteapp.di

import com.alitafreshi.constance.Constance.APP_BASE_URL
import com.alitafreshi.domain.remote.BaseResponse
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RemoteModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory =
        GsonConverterFactory.create(gson)

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient,gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(APP_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

}