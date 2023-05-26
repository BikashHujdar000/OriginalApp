package com.example.hotelreservationsystem.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hotelreservationsystem.Models.BookRequest
import com.example.hotelreservationsystem.Models.BookingResponse
import com.example.hotelreservationsystem.Models.HotelResponse
import com.example.hotelreservationsystem.Models.HotelResponseListMethod
import com.example.hotelreservationsystem.utils.NetworkResult
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface getallHotelsApi {

   @GET("user/getallhotel/{userId}")
   suspend fun getallHotels(@Path ("userId")userId:String): Response<HotelResponseListMethod>


   // yaha  lekh xu man sab bookigs ko code because yasley malae interceptors bala retrofit client di rahexa

   // writing functions to book the room
//https://anxious-gaiters-bee.cyclic.app/user/bookroom/647034fa7f9bac4c2cb46119/646da91c4cd9ef9e30a5ad4a/646deb68e1b59be01864c43d
    @POST("user/bookroom/{userId}/{hotelId}/{roomId}")
    suspend fun bookRoom(@Path("userId")userId: String,@Path("hotelId")hotelId:String,@Path("roomId")roomId:String,@Body bookRequest: BookRequest):Response<HotelResponse>

    //interface to get bookings of users


//https://anxious-gaiters-bee.cyclic.app/user/getallbooking/647034fa7f9bac4c2cb46119
    @GET("user/getallbooking/{userID")
    suspend fun userBookings(@Path("userId")userId:String):Response<BookingResponse>

}