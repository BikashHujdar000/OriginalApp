package com.example.hotelreservationsystem.Models

data class BookingX(
    val __v: Int,
    val _id: String,
    val endDate: String,
    val hotel: HotelX,
    val isActive: Boolean,
    val room: RoomXX,
    val startDate: String,
    val user: UserXX
)