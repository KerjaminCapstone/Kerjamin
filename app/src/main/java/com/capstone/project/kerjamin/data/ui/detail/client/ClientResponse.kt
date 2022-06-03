package com.capstone.project.kerjamin.data.ui.detail.client

import com.capstone.project.kerjamin.data.ui.detail.client.ClientModel
import com.google.gson.annotations.SerializedName

data class ClientResponse(
    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("data")
    val data: ArrayList<ClientModel>
)
