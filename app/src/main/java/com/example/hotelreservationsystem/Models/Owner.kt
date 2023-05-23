package com.example.hotelreservationsystem.Models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

data class Owner(
    val __v: Int,
    val _id: String,
    val createdAt: String,
    val email: String,
    val hotel: List<Any>,
    val isOwner: Boolean,
    val ownername: String,
    val password: String,
    val phone: Long,
    val updatedAt: String
): Serializable