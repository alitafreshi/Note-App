package com.alitafreshi.domain.remote

import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
@Keep
data class BaseResponseStatus(
    @SerializedName("code")
    val code: Int,
    @SerializedName("description")
    val description: String
)
