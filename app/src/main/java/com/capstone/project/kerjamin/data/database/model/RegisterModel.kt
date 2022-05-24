package com.capstone.project.kerjamin.data.database.model

data class RegisterModel(
    val nik: String,
    val username: String,
    val nama: String,
    val alamat: String,
    val jenis_kelamin: String,
    val tanggal_lahir: String,
    val no_wa: String,
    val email: String,
    val password: String,
    val role: String
)
