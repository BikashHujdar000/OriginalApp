package com.example.hotelreservationsystem.api

import com.example.hotelreservationsystem.Models.HotelRequest
import com.example.hotelreservationsystem.Models.HotelResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface HotelsApi {

  @POST("hotel/createhotel/{ownerId}")
  suspend fun createHotel(@Path("ownerId") ownerId:String,@Body hotelRequest: HotelRequest): Response<HotelResponse>

// method  for add room fragment
}