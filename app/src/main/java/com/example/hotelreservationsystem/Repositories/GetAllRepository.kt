package com.example.hotelreservationsystem.Repositories
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hotelreservationsystem.Models.BookHudaKoResponse
import com.example.hotelreservationsystem.Models.BookRequest
import com.example.hotelreservationsystem.Models.FinalBookingResponse
import com.example.hotelreservationsystem.Models.HotelResponse
import com.example.hotelreservationsystem.Models.HotelResponseListMethod
import com.example.hotelreservationsystem.api.getallHotelsApi
import com.example.hotelreservationsystem.utils.NetworkResult
import com.example.hotelreservationsystem.utils.constants
import com.example.hotelreservationsystem.utils.constants.TAG
import retrofit2.Response
import javax.inject.Inject

class GetAllRepository @Inject constructor( private val getallHotelsApi: getallHotelsApi) {


private val _hotelLiveDataList = MutableLiveData<NetworkResult<HotelResponseListMethod>>()
    val hotelLiveDataList : LiveData<NetworkResult<HotelResponseListMethod>>
        get() = _hotelLiveDataList


    private  val _bookNowResponseLiveData = MutableLiveData<NetworkResult<BookHudaKoResponse>>()
    val bookNowResponseLiveData :LiveData<NetworkResult<BookHudaKoResponse>>
        get() = _bookNowResponseLiveData
    // let me describe new
    private  val _hotelLiveData =MutableLiveData<NetworkResult<HotelResponse>>()
    val hotelLiveData : LiveData<NetworkResult<HotelResponse>>
        get() = _hotelLiveData


    private val _statusLiveData = MutableLiveData<NetworkResult<String>>()
    val statusLiveData :LiveData<NetworkResult<String>>
        get()= _statusLiveData


    private  val _userBookingsLiveData = MutableLiveData<NetworkResult<FinalBookingResponse>>()
    val userBookLiveData :LiveData<NetworkResult<FinalBookingResponse>>
        get() = _userBookingsLiveData


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
    {_bookNowResponseLiveData.postValue(NetworkResult.Loading())

        val response = getallHotelsApi.bookRoom(userId,hotelId,roomId,bookRequest)

       Log.d(TAG,"${response.body()}")
        if (response.isSuccessful && response.body()!= null)
        {
            Log.d(TAG, "response aaudee xa  ${response.body()}")
            _bookNowResponseLiveData.postValue(NetworkResult.Success(response.body()!!))
        }


    }

    suspend fun userBookings(userId: String)
    {
        _userBookingsLiveData.postValue(NetworkResult.Loading())
       val response =  getallHotelsApi.userBookings(userId)
//        if (response.isSuccessful)
//        {
//            Log.d(TAG,"Okay response is genetrated ")
//            val responseData = response.body()
//            Log.d(TAG,"${responseData.toString()}")
//        }

        handleResponseofBooking(response)
    }

    suspend fun  cancelBooking(userId:String,hotelId: String,roomId: String,bookingId: String)
    {
      val response =  getallHotelsApi.cancelBooking(userId,hotelId,roomId,bookingId)

        if(response.isSuccessful && response.body()!= null)
        {
            handleResponseofBooking(response)
        }
        else
        {
            Log.d(TAG,"Response generation Failed")
        }
    }


    private fun handleResponseofBooking(response: Response<FinalBookingResponse>) {
        if (response.isSuccessful && response.body() != null) {
            _userBookingsLiveData.postValue(NetworkResult.Success(response.body()!!))
        } else {
            _userBookingsLiveData.postValue(NetworkResult.Error("Something Went Wrong"))
        }
    }


}