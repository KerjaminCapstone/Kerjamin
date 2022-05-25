package com.capstone.project.kerjamin.data.database.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.sql.Time

@Parcelize
data class ClientModel(
    val name: String,
    val email: String,
    val house_pict: String,
    val no_wa: String,
    val address: String,
    val address_long: Float,
    val address_lat: Float,
    val is_male: Boolean,
    val dob: Time,
    val nik: String,
    val profile_pict: String
):Parcelable
