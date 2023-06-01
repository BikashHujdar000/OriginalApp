package com.example.hotelreservationsystem.api

import com.example.hotelreservationsystem.Models.RecommendationRequest
import com.example.hotelreservationsystem.Models.RecoomenderResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface okayTestApio {
  //  @POST("ownerauth/generateotp1")
  //    suspend fun getOtp(@Body otpGenerateRequest: OtpGenerateRequest):Response<OtpGenerateResponse>


  //  http://linaanil.pythonanywhere.com/api/predict
    @POST("api/predict")
    suspend fun  getList(@Body recommendationRequest: RecommendationRequest):Response<RecoomenderResponse>

//https://anxious-gaiters-bee.cyclic.app/user/getsinglehotelnoauth/6476b4d29ed3399ac6594e3d/6476b84a8c919b4666f84e3f


}