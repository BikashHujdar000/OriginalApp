package com.example.hotelreservationsystem.api

import com.example.hotelreservationsystem.Models.OwnerRequest
import com.example.hotelreservationsystem.Models.OwnerResponse
import com.example.hotelreservationsystem.Models.PhotosResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part


interface OwnerApi {

     @POST("/ownerauth/register1")
    suspend fun signUp( @Body ownerRequest: OwnerRequest):Response<OwnerResponse>

    @POST("/ownerauth/login1")
    suspend fun signIn( @Body ownerRequest: OwnerRequest):Response<OwnerResponse>
     @Multipart
    @POST("/ownerroom/uploadroompictocloud")
    suspend fun uplaodImage(@Part image:MultipartBody.Part):Response<PhotosResponse>
}
