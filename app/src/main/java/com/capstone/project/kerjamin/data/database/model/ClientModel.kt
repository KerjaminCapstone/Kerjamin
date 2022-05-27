package com.capstone.project.kerjamin.data.database.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.sql.Time

@Parcelize
data class ClientModel(
    val name: String,
    val email: String,
    val no_wa: String,
    val address: String,
    val address_long: Float,
    val address_lat: Float,
    val jenis_kelamin: String,
    val nik: String
):Parcelable
