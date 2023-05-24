package com.example.hotelreservationsystem.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hotelreservationsystem.Models.HotelResponse
import com.example.hotelreservationsystem.Models.HotelResponseListMethod
import com.example.hotelreservationsystem.utils.NetworkResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface getallHotelsApi {

   @GET("user/getallhotel/{userId}")
   suspend fun getallHotels(@Path ("userId")userId:String): Response<HotelResponseListMethod>
}