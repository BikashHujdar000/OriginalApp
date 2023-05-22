package com.example.hotelreservationsystem.api

import com.example.hotelreservationsystem.Models.OwnerRequest
import com.example.hotelreservationsystem.Models.OwnerResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface OwnerApi {

     @POST("/ownerauth/register1")
    suspend fun signUp( @Body ownerRequest: OwnerRequest):Response<OwnerResponse>

    @POST("/ownerauth/login1")
    suspend fun signIn( @Body ownerRequest: OwnerRequest):Response<OwnerResponse>
}
