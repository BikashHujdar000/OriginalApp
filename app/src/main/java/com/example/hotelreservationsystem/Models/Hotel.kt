package com.example.hotelreservationsystem.Models

import java.io.Serializable

data class Hotel(
    val __v: Int,
    val _id: String,
    val address: String,
    val createdAt: String,
    val description: String,
    val name: String,
    val owner: Owner,
    val user: User,
    val photos: List<String>,
    val review: List<Any>,
    val rooms: List<Room>,
    val updatedAt: String
):Serializable