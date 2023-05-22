package com.example.hotelreservationsystem.ViewModels

import android.text.TextUtils
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotelreservationsystem.Models.UserRequest
import com.example.hotelreservationsystem.Models.UserResponse
import com.example.hotelreservationsystem.Repositories.UserRepository
import com.example.hotelreservationsystem.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserAuthViewModel @Inject constructor( private val userRepository: UserRepository): ViewModel() {

    // getting user response livedata
    val userResponseLiveData: LiveData<NetworkResult<UserResponse>>
        get() = userRepository.userResponseLiveData
    fun registerUser(userRequest: UserRequest) {
        viewModelScope.launch {
            userRepository.registerUser(userRequest)
        }
    }

    fun loginUser(userRequest: UserRequest) {
        viewModelScope.launch {
            userRepository.loginUser(userRequest)
        }

    }

    fun validateCredential(userName:String, userEmailAddress:String, userPassword:String, isUserLoginFragment:Boolean):Pair<Boolean,String>
    {
        var result = Pair(true,"")

        if( !isUserLoginFragment && TextUtils.isEmpty(userName)  || TextUtils.isEmpty(userEmailAddress)  || TextUtils.isEmpty(userPassword))
        {
            result = Pair(false,"Please provide the credential")
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(userEmailAddress).matches())
        {
            result = Pair(false,"Please enter Valid EmailAddress")
        }

        else if(userPassword.length  <= 6)
        {
            result = Pair(false,"Password length should be greater than 6")
        }
        return  result

    }
}