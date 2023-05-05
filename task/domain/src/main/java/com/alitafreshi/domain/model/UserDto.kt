package com.alitafreshi.domain.model

import com.google.gson.annotations.SerializedName


data class UserDto(
    @SerializedName("userId")
    val userId: Long,

    @SerializedName("phoneNumber")
    val phoneNumber: String
)
