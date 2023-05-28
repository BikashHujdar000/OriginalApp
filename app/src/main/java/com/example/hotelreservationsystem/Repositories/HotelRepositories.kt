package com.example.hotelreservationsystem.Repositories

import android.nfc.Tag
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hotelreservationsystem.Models.AllbookingsResponse
import com.example.hotelreservationsystem.Models.HotelRequest
import com.example.hotelreservationsystem.Models.HotelResponse
import com.example.hotelreservationsystem.Models.RoomRequest
import com.example.hotelreservationsystem.api.HotelsApi
import com.example.hotelreservationsystem.utils.NetworkResult
import com.example.hotelreservationsystem.utils.constants.TAG
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

class HotelRepositories @Inject constructor(private  val hotelsApi: HotelsApi) {

    private  val _hotelLiveData =MutableLiveData<NetworkResult<HotelResponse>>()
    val hotelLiveData : LiveData<NetworkResult<HotelResponse>>
            get() = _hotelLiveData


    private val _statusLiveData = MutableLiveData<NetworkResult<String>>()
    val statusLiveData :LiveData<NetworkResult<String>>
        get()= _statusLiveData

     private val _allBookingLiveData = MutableLiveData<NetworkResult<AllbookingsResponse>>()
    val allbookingsResponse : LiveData<NetworkResult<AllbookingsResponse>>
        get() = _allBookingLiveData


    suspend fun createHotel(ownerId:String,hotelRequest: HotelRequest)

    {
        _statusLiveData.postValue(NetworkResult.Loading())
        _hotelLiveData.postValue(NetworkResult.Loading())

        val response = hotelsApi.createHotel(ownerId ,hotelRequest)
        dothis(response)

    }


    private fun dothis(response: Response<HotelResponse>) {
        if (response.isSuccessful && response.body() != null) {
            _hotelLiveData.postValue(NetworkResult.Success(response.body()!!))
        } else {
            val errotObj = JSONObject(response.errorBody()!!.charStream().readText())
            _hotelLiveData.postValue(NetworkResult.Error(errotObj.getString("error")))

        }
        handleresponse(response, "Hotel Created")
    }

    suspend fun  addRoom(ownerId: String,hotelId:String,roomRequest: RoomRequest) {

        _statusLiveData.postValue(NetworkResult.Loading())
        _hotelLiveData.postValue(NetworkResult.Loading())
          val response = hotelsApi.addRoom(ownerId,hotelId,roomRequest)
        if(response.isSuccessful && response.body()!= null)

        {
            _hotelLiveData.postValue(NetworkResult.Success(response.body()!!))
        }
        else
        {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _hotelLiveData.postValue((NetworkResult.Error(errorObj.getString("error"))))
        }
        handleresponse(response,"Room Added Success")



    }



    suspend fun getAllRoom(ownerId: String,hotelId: String)
    {
        _statusLiveData.postValue(NetworkResult.Loading())
        _hotelLiveData.postValue(NetworkResult.Loading())

        val response = hotelsApi.getHotelsRoom(ownerId,hotelId)
        handleOriginalResponse(response)
        handleresponse(response,"response Sucess and  all the data are geted")

    }


    suspend fun deleteRoom(ownerId: String,hotelId: String,roomId:String)
    {

        _statusLiveData.postValue(NetworkResult.Loading())
        _hotelLiveData.postValue(NetworkResult.Loading())
        val response = hotelsApi.deleteRoom(ownerId,hotelId,roomId)
        handleOriginalResponse(response)
        handleresponse(response,"Deleted Sucessfully")
    }

    suspend fun  updateRoom(ownerId: String,hotelId: String,roomId: String,roomRequest: RoomRequest)
    {
        _statusLiveData.postValue(NetworkResult.Loading())
        _hotelLiveData.postValue(NetworkResult.Loading())
        val response = hotelsApi.updateRoom(ownerId,hotelId,roomId,roomRequest )
        handleresponse(response,"Hotel Updated Successfully")
        handleOriginalResponse(response)
    }
      suspend fun showBooking(ownerId: String){
          val response = hotelsApi.showBookings(ownerId)
          if(response.isSuccessful&&response.body()!==null){
              _allBookingLiveData.postValue(NetworkResult.Success(response.body()!!))
          }
          else{
              Log.d( TAG,"there is error in getting bookings details ")
          }
      }



    // outside all the functions
    private fun handleresponse(response: Response<HotelResponse>,message:String) {
                if (response.isSuccessful && response.body() != null) {
                    Log.d(TAG,response.body().toString())
                    _statusLiveData.postValue(NetworkResult.Success(message))

        } else {
            _statusLiveData.postValue(NetworkResult.Error("Something Went Wrong"))
            Log.d(TAG,"Maile response pasko xaina")
        }
    }
    private fun handleAllHotelresponse(response: Response<List<HotelResponse>>,message:String) {
        if (response.isSuccessful && response.body() != null) {
            Log.d(TAG,response.body().toString())
            _statusLiveData.postValue(NetworkResult.Success(message))

        } else {
            _statusLiveData.postValue(NetworkResult.Error("Something Went Wrong"))
            Log.d(TAG,"hotel information is not found yet")
        }
    }



    private fun handleOriginalResponse(response:Response<HotelResponse>)
    {
        if(response.isSuccessful && response.body()!= null)
        {
            _hotelLiveData.postValue(NetworkResult.Success(response.body()!!))
        }
        else
        {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _hotelLiveData.postValue(NetworkResult.Error(errorObj.getString("error")))
        }

    }

}
