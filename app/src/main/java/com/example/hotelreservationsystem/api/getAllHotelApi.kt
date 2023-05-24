package com.example.hotelreservationsystem.api

import com.example.hotelreservationsystem.Models.HotelResponse
import com.example.hotelreservationsystem.Models.UserRequest
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface getAllHotelApi {
    @GET("user/getallhotel/{userID}")
    suspend fun getAllHotel(@Path("userId") userRequest: UserRequest) : Response<List<HotelResponse>>
}