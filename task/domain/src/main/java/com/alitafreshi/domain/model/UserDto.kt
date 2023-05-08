package com.alitafreshi.domain.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class UserDto(
    @SerializedName("userId")
    val userId: Int,

    @SerializedName("phoneNumber")
    val phoneNumber: String
)
