package com.alitafreshi.domain.remote

import androidx.annotation.Keep

import com.google.gson.annotations.SerializedName

@Keep
open class BaseResponse<T>(

    @SerializedName("response")
    val response: T,
    @SerializedName("status")
    val status: BaseResponseStatus?

)
