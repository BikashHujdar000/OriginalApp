package com.example.hotelreservationsystem.Models

data class Booking(
    val __v: Int,
    val _id: String,
    val endDate: String,
    val hotel: HotelXX,
    val isActive: Boolean,
    val room: RoomX,
    val startDate: String,
    val user: UserX
)