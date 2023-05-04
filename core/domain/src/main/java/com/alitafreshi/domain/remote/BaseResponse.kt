package com.alitafreshi.domain.remote

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(

    @SerializedName("response")
    val response: T,

    @SerializedName("status")
    val status: BaseResponseStatus

)
