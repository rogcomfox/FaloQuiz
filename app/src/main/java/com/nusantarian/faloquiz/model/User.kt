package com.nusantarian.faloquiz.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val email: String,
    val name: String,
    val picture: String
) : Parcelable