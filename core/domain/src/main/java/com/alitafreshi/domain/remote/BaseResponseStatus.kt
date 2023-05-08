package com.alitafreshi.domain.remote

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
@Keep
data class BaseResponseStatus(
    @SerializedName("code")
    val code: Int,

    @SerializedName("code")
    val description: String
)
