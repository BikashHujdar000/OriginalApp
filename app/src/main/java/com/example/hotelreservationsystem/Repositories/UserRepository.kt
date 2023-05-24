package com.example.hotelreservationsystem.Repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hotelreservationsystem.Models.UserRequest
import com.example.hotelreservationsystem.Models.UserResponse
import com.example.hotelreservationsystem.api.UserApi
import com.example.hotelreservationsystem.utils.NetworkResult
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

class UserRepository @Inject constructor( private val userApi: UserApi){
    // setting live data

    private val _userResponseLiveData = MutableLiveData<NetworkResult<UserResponse>>()
    val userResponseLiveData: LiveData<NetworkResult<UserResponse>>

        get() = _userResponseLiveData

    // repos Function that calls owner api functions

    suspend fun registerUser(userRequest: UserRequest)
    {
        _userResponseLiveData.postValue(NetworkResult.Loading())

        val response = userApi.signUp(userRequest)
        handleResponse(response)
    }
    suspend fun  loginUser(userRequest: UserRequest){
        _userResponseLiveData.postValue(NetworkResult.Loading())

        val response = userApi.signIn(userRequest)

        handleResponse(response)
    }
    private fun handleResponse(response: Response<UserResponse>) {
        if (response.isSuccessful && response.body() != null) {
            _userResponseLiveData.postValue(NetworkResult.Success(response.body()!!))
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _userResponseLiveData.postValue(NetworkResult.Error(errorObj.getString("error")))
        } else {
            _userResponseLiveData.postValue(NetworkResult.Error("Something Went Wrong"))
        }
    }

}
