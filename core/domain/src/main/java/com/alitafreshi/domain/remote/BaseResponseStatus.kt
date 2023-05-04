package com.alitafreshi.domain.remote

import com.google.gson.annotations.SerializedName

data class BaseResponseStatus(
    @SerializedName("code")
    val code: Int,

    @SerializedName("code")
    val description: String
)
