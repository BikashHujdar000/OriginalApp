package com.example.hotelreservationsystem.api

import com.example.hotelreservationsystem.Models.TestModelResponse
import retrofit2.Response
import retrofit2.http.GET

interface okayTestApio {
  //  @POST("ownerauth/generateotp1")
  //    suspend fun getOtp(@Body otpGenerateRequest: OtpGenerateRequest):Response<OtpGenerateResponse>

  @GET("entries")
  suspend fun getDetails():Response<TestModelResponse>

}