package com.capstone.project.kerjamin.data.ui.detail.freelancer

import com.google.gson.annotations.SerializedName

data class DetailFreelancerResponse(
    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("data")
    val data: ArrayList<DetailFreelancerModel>
)
