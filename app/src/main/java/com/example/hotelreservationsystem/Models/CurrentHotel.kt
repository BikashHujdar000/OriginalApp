package com.example.hotelreservationsystem.Models

data class CurrentHotel(
    val __v: Int,
    val _id: String,
    val address: String,
    val createdAt: String,
    val description: String,
    val name: String,
    val owner: OwnerX,
    val photos: List<Any>,
    val review: List<Any>,
    val rooms: List<Any>,
    val updatedAt: String
)
