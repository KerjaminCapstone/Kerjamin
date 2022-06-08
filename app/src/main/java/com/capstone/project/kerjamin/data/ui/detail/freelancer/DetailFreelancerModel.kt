package com.capstone.project.kerjamin.data.ui.detail.freelancer

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailFreelancerModel(
    val bidang : String,
    val keahlian : String,
    val nama : String,
    val alamat : String,
    val jenis_kelamin: String,
    val jarak : String,
    val tag_nlp : ArrayList<SpanModel>,
):Parcelable

