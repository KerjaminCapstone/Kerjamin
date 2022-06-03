package com.capstone.project.kerjamin.data.ui.auth.response

import com.google.gson.annotations.SerializedName

data class ResponseLogin(

    @field:SerializedName("error")
    val error: Boolean,

    @SerializedName("token")
    val token: String,

    @SerializedName("isLogin")
    val isLogin: Boolean
)
