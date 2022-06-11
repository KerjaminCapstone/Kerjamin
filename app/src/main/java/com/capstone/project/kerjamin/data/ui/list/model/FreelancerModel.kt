package com.capstone.project.kerjamin.data.ui.list.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FreelancerModel(
    val id_freelance : String,
    val name : String,
    val is_trainee : Boolean,
    val rating : Double,
    val job_done : String,
    val date_join : String,
    val jenis_kelamin : String,
    val distance_haversign : Double,
    val job_child_name : String,
    val address : String,
    val address_lat : Double,
    val address_long : Double,
    val distance : String
): Parcelable

