package com.capstone.project.kerjamin.data.ui.list.response

import com.capstone.project.kerjamin.data.ui.list.model.FreelancerModel
import com.google.gson.annotations.SerializedName

data class FreelancerResponse(
    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("data")
    val data: ArrayList<FreelancerModel>
)
