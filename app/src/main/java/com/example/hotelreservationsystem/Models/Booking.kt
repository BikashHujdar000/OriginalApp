package com.example.hotelreservationsystem.Models

data class Booking(
    val __v: Int,
    val _id: String,
    val endDate: String,
    val hotel: Hotel,
    val isActive: Boolean,
    val room: Room,
    val startDate: String,
    val user: User
)