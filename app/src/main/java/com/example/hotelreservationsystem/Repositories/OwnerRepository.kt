package com.example.hotelreservationsystem.Repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hotelreservationsystem.Fragments.otpRequest
import com.example.hotelreservationsystem.Models.OtpResponse
import com.example.hotelreservationsystem.Models.OwnerRequest
import com.example.hotelreservationsystem.Models.OwnerResponse
import com.example.hotelreservationsystem.Models.PhotosResponse
import com.example.hotelreservationsystem.api.OwnerApi
import com.example.hotelreservationsystem.utils.NetworkResult
import com.example.hotelreservationsystem.utils.constants.TAG
import okhttp3.MultipartBody
import org.json.JSONObject
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class OwnerRepository  @Inject constructor ( private val ownerApi:OwnerApi) {

    private val _photosResponseLiveData = MutableLiveData<NetworkResult<PhotosResponse>>()
    val photoResponseLiveData: LiveData<NetworkResult<PhotosResponse>>
        get() = _photosResponseLiveData

    //setting of live data
    private val _ownerResponseLiveData = MutableLiveData<NetworkResult<OwnerResponse>>()
    val ownerResponseLiveData: LiveData<NetworkResult<OwnerResponse>>
        get() = _ownerResponseLiveData

    private val _otpResponseLiveData = MutableLiveData<NetworkResult<OtpResponse>>()
    val otpResponseLiveData: LiveData<NetworkResult<OtpResponse>>
        get() = _otpResponseLiveData

    // repos Function that calls owner api functions

    suspend fun registerOwner(ownerRequest: OwnerRequest) {
        _ownerResponseLiveData.postValue(NetworkResult.Loading())

        val response = ownerApi.signUp(ownerRequest)
        handleResponse(response)
    }

    suspend fun uploadImage(image: MultipartBody.Part) {
        val response = ownerApi.uplaodImage(image)
        if (response.isSuccessful && response.body() != null) {
            _photosResponseLiveData.postValue(NetworkResult.Success(response.body()))
        } else {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _photosResponseLiveData.postValue(NetworkResult.Error(errorObj.getString("error")))
        }
    }

    suspend fun loginOwner(ownerRequest: OwnerRequest) {
        _ownerResponseLiveData.postValue(NetworkResult.Loading())
        val response = ownerApi.signIn(ownerRequest)
        handleResponse(response)
    }

    private fun handleResponse(response: Response<OwnerResponse>) {
        if (response.isSuccessful && response.body() != null) {
            _ownerResponseLiveData.postValue(NetworkResult.Success(response.body()!!))
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _ownerResponseLiveData.postValue(NetworkResult.Error(errorObj.getString("error")))
        } else {
            _ownerResponseLiveData.postValue(NetworkResult.Error("Something Went Wrong"))
        }
    }

    suspend fun getOtp(otpRequest: otpRequest) {
        Log.d(TAG,"response lyauney thaw ")

        try {
            val response = ownerApi.getOtp(otpRequest)
            if (response.isSuccessful && response.body() != null) {
            Log.d("respomse","response generated")
            _otpResponseLiveData.postValue(NetworkResult.Success(response.body()!!))
        }
        else
        {
            Log.d(TAG,"error on getting the response")
        }

        }catch (e:Exception)

        {
            Log.d(TAG,"error on getting the response")
        }


    }
}