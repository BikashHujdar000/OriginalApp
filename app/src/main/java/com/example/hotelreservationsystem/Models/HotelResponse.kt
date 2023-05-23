package com.example.hotelreservationsystem.Models

data class HotelResponse(
    val allHotel: List<AllHotel>,
    val currentHotel: CurrentHotel
)