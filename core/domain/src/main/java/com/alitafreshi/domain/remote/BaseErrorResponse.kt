package com.alitafreshi.domain.remote

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class BaseErrorResponse(
    @SerializedName("errorMessage")
    val errorMessage: String,

    @SerializedName("requestStatus")
    val requestStatus: BaseResponseStatus
)
