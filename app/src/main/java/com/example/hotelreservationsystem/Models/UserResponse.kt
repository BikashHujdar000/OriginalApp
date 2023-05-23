package com.example.hotelreservationsystem.Models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class UserResponse(
    val token: String,
    val user: @RawValue User
):Parcelable