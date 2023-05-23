package com.example.hotelreservationsystem.api

import com.example.hotelreservationsystem.Models.UserRequest
import com.example.hotelreservationsystem.Models.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
interface UserApi {
    @POST("/userauth/register2/")
    suspend fun signUp(@Body userRequest: UserRequest): Response<UserResponse>

    @POST("/userauth/login2/")
    suspend fun signIn(@Body userRequest: UserRequest): Response<UserResponse>
}