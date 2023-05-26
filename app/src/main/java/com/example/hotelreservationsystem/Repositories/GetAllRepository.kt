package com.example.hotelreservationsystem.Repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hotelreservationsystem.Models.BookRequest
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


    // let me describe new
    private  val _hotelLiveData =MutableLiveData<NetworkResult<HotelResponse>>()
    val hotelLiveData : LiveData<NetworkResult<HotelResponse>>
        get() = _hotelLiveData


    private val _statusLiveData = MutableLiveData<NetworkResult<String>>()
    val statusLiveData :LiveData<NetworkResult<String>>
        get()= _statusLiveData



    suspend fun  getAllHotel(userId:String)
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
    }
    suspend fun bookRoom(userId: String,hotelId:String,roomId:String,bookRequest: BookRequest)
    {
       _statusLiveData.postValue(NetworkResult.Loading())
        _hotelLiveData.postValue(NetworkResult.Loading())

        val response = getallHotelsApi.bookRoom(userId,hotelId,roomId,bookRequest)
        if (response.isSuccessful && response.body()!= null)
        {
            _statusLiveData.postValue(NetworkResult.Success(" Sucessfully Booked Hotel"))
            _hotelLiveData.postValue(NetworkResult.Success(response.body()!!))
        }
        else
        {
            _hotelLiveData.postValue(NetworkResult.Error("Something went wrong Sorry"))
        }

    }

    suspend fun userBookings(userId: String)
    {
       val response =  getallHotelsApi.userBookings(userId)
    }



}