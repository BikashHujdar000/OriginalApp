package com.example.hotelreservationsystem.Repositories

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hotelreservationsystem.Models.OwnerRequest
import com.example.hotelreservationsystem.Models.OwnerResponse
import com.example.hotelreservationsystem.api.OwnerApi
import com.example.hotelreservationsystem.utils.NetworkResult
import com.example.hotelreservationsystem.utils.constants.TAG
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

class OwnerRepository  @Inject constructor ( private val ownerApi:OwnerApi){
    //setting of live data
    private val _ownerResponseLiveData = MutableLiveData<NetworkResult<OwnerResponse>>()
    val ownerResponseLiveData:LiveData<NetworkResult<OwnerResponse>>
        get() = _ownerResponseLiveData

    // repos Function that calls owner api functions

    suspend fun registerOwner(ownerRequest: OwnerRequest)
    {
        _ownerResponseLiveData.postValue(NetworkResult.Loading())
        val response = ownerApi.signUp(ownerRequest)
        handleResponse(response)
    }
    suspend fun  loginOwner(ownerResponse: OwnerResponse){

        val response = ownerApi.signIn(ownerResponse)
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


}