package com.capstone.project.kerjamin.data.database.response

import com.capstone.project.kerjamin.data.database.model.ClientModel
import com.google.gson.annotations.SerializedName

data class ClientResponse(
    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("data")
    val data: ArrayList<ClientModel>
)
