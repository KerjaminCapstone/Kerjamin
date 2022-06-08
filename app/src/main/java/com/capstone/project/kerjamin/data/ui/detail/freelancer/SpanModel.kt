package com.capstone.project.kerjamin.data.ui.detail.freelancer

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SpanModel(
    val id_freelance_nlp : Int,
    val id_freelance : String,
    val nlp_tag1 : String,
    val nlp_tag2 : String,
    val nlp_tag3 : String,
    val nlp_tag4 : String,
    val nlp_tag5 : String
):Parcelable
