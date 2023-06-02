package com.example.hotelreservationsystem.Models

data class HotelX(
    val __v: Int,
    val _id: String,
    val address: String,
    val cheapestPrice: Int,
    val createdAt: String,
    val description: String,
    val name: String,
    val owner: String,
    val photos: List<String>,
    val rating: Int,
    val review: List<Any>,
    val rooms: List<String>,
    val type: Int,
    val updatedAt: String
)