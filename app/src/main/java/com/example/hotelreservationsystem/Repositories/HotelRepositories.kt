package com.example.hotelreservationsystem.Repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hotelreservationsystem.Models.HotelRequest
import com.example.hotelreservationsystem.Models.HotelResponse
import com.example.hotelreservationsystem.api.HotelsApi
import com.example.hotelreservationsystem.utils.NetworkResult
import retrofit2.Response
import javax.inject.Inject

class HotelRepositories @Inject constructor(private  val hotelsApi: HotelsApi) {
    private  val _hotelLiveData =MutableLiveData<NetworkResult<List<HotelResponse>>>()
        val hotelLiveData : LiveData<NetworkResult<List<HotelResponse>>>
            get() = _hotelLiveData


    private val _statusLiveData = MutableLiveData<NetworkResult<String>>()
    val statusLiveData :LiveData<NetworkResult<String>>
        get()= _statusLiveData


    suspend fun createHotel(ownerId:String,hotelRequest: HotelRequest)

    {
        _statusLiveData.postValue(NetworkResult.Loading())
        val response = hotelsApi.createHotel(ownerId ,hotelRequest)
        handleresponse(response,"Hotel Created")

    }


    // outside all the functions
    private fun handleresponse(response: Response<HotelResponse>,message:String) {
        if (response.isSuccessful && response.body() != null) {
            _statusLiveData.postValue(NetworkResult.Success(message))

        } else {
            _statusLiveData.postValue(NetworkResult.Error("Something Went Wrong"))
        }
    }


}
