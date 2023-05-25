package com.example.hotelreservationsystem.api

import com.example.hotelreservationsystem.Models.HotelRequest
import com.example.hotelreservationsystem.Models.HotelResponse
import com.example.hotelreservationsystem.Models.RoomRequest
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface  HotelsApi {

  @POST("hotel/createhotel/{ownerId}")
  suspend fun createHotel(@Path("ownerId") ownerId: String, @Body hotelRequest: HotelRequest): Response<HotelResponse>
// method  for add room fragment

  @Multipart
  @POST("ownerroom/createroom/{ownerId}/{hotelId}")
  suspend fun addRoom(
    @Part("ownerID") ownerID: RequestBody,
    @Part("hotelID") hotelID: RequestBody,
    @Part("number") number: RequestBody,
    @Part("type") type: RequestBody,
    @Part("price") price: RequestBody,
    @Part image: MultipartBody.Part

  ): Response<HotelResponse>


// method  for add room fragment
  @POST("ownerroom/createroom/{ownerId}/{hotelId}")
  suspend fun  addRoom(@Path ("ownerId")ownerId:String,@Path("hotelId") hotelId:String,@Body roomRequest: RoomRequest):Response<HotelResponse>

  @GET("ownerroom/getallroom/{ownerId}/{hotelId}")
  suspend fun getHotelsRoom(@Path("ownerId")ownerId: String,@Path("hotelId")hotelId: String):Response<HotelResponse>

  @DELETE("ownerroom/deleteroom/{ownerId}/{hotelId}/{roomId}")
  suspend fun deleteRoom(@Path("ownerId")ownerId: String,@Path("hotelId")hotelId: String,@Path("roomId")roomId: String):Response<HotelResponse>

  @PUT ("ownerroom/updateroom/{ownerId}/{hotelId}/{roomId}")
  suspend fun updateRoom(@Path("ownerId")ownerId: String,@Path("hotelId")hotelId: String,@Path("roomId")roomId: String,@Body roomRequest: RoomRequest):Response<HotelResponse>


}

