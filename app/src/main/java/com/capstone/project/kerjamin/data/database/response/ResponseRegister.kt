package com.capstone.project.kerjamin.data.database.response

import com.google.gson.annotations.SerializedName

data class ResponseRegister(

    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String
)
