package com.capstone.project.kerjamin.data.ui.list.model

import android.os.Parcel
import android.os.Parcelable

data class Freelancer(val image : Int, val namaFreelancer : String, val bidang : String, val distance : String, val rating : String) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(image)
        parcel.writeString(namaFreelancer)
        parcel.writeString(bidang)
        parcel.writeString(distance)
        parcel.writeString(rating)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Freelancer> {
        override fun createFromParcel(parcel: Parcel): Freelancer {
            return Freelancer(parcel)
        }

        override fun newArray(size: Int): Array<Freelancer?> {
            return arrayOfNulls(size)
        }
    }

}
