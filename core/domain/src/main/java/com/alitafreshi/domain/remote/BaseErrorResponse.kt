package com.alitafreshi.domain.remote

import com.google.gson.annotations.SerializedName

data class BaseErrorResponse(
    @SerializedName("errorMessage")
    val errorMessage: String,

    @SerializedName("requestStatus")
    val requestStatus: BaseResponseStatus
)
