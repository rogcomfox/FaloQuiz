package com.nusantarian.faloquiz.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val email: String,
    val pass: String,
    val name: String?,
    val confirm: String?
) : Parcelable