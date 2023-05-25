package com.example.hotelreservationsystem.Repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hotelreservationsystem.Models.HotelResponse
import com.example.hotelreservationsystem.Models.HotelResponseListMethod
import com.example.hotelreservationsystem.api.getallHotelsApi
import com.example.hotelreservationsystem.utils.NetworkResult
import retrofit2.Response
import javax.inject.Inject

class GetAllRepository @Inject constructor( private val getallHotelsApi: getallHotelsApi) {
private val _hotelLiveDataList = MutableLiveData<NetworkResult<HotelResponseListMethod>>()
    val hotelLiveDataList : LiveData<NetworkResult<HotelResponseListMethod>>
        get() = _hotelLiveDataList

    suspend fun  getAllHotel(userId:String): Response<HotelResponseListMethod>
    {
        val response=  getallHotelsApi.getallHotels(userId)
        if (response.isSuccessful && response.body()!=null){
            _hotelLiveDataList.postValue(NetworkResult.Success(response.body()!!))
        }
        else if (response.errorBody()!=null){
            _hotelLiveDataList.postValue(NetworkResult.Error<HotelResponseListMethod>("something went wrong"))
        }
        else{
            _hotelLiveDataList.postValue(NetworkResult.Error<HotelResponseListMethod>("something went wrong"))
        }
        return response
    }
}