package com.example.hotelreservationsystem.Models

data class Hotel(
    val __v: Int,
    val _id: String,
    val address: String,
    val createdAt: String,
    val description: String,
    val name: String,
    val owner: Owner,
    val photos: List<Any>,
    val review: List<Any>,
    val rooms: List<Room>,
    val updatedAt: String
)