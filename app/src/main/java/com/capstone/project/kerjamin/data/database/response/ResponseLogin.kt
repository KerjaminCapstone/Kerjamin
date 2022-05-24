package com.capstone.project.kerjamin.data.database.response

import com.google.gson.annotations.SerializedName

data class ResponseLogin(

    @field:SerializedName("message")
    val message: String,

    @SerializedName("token")
    val token: String
)
