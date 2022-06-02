package com.capstone.project.kerjamin.data.ui.auth.register

import okhttp3.RequestBody

data class RegisterModel(
    val nik: String,
    val nama: String,
    val alamat: String,
    val jenis_kelamin: String,
    val no_wa: String,
    val email: String,
    val password: String,
    val role: String,
    val latitude: RequestBody,
    val longitude: RequestBody
)
