package com.capstone.project.kerjamin.data.ui.list.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FreelancerModel(
    val id_freelance : String,
    val name : String,
    val is_trainee : Boolean,
    val rating : String,
    val job_done : String,
    val date_join : String,
    val jenis_kelamin : String,
    val distance : Double,
    val job_child_name : String
): Parcelable
