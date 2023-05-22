package com.example.hotelreservationsystem.Models

data class HotelResponse(
    val __v: Int,
    val _id: String,
    val address: String,
    val createdAt: String,
    val description: String,
    val name: String,
    val photos: List<String>,
    val review: List<Any>,
    val rooms: List<Any>,
    val updatedAt: String
)