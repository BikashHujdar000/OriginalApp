package com.example.hotelreservationsystem.ViewModels

import android.text.TextUtils
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotelreservationsystem.Models.OwnerRequest
import com.example.hotelreservationsystem.Models.OwnerResponse
import com.example.hotelreservationsystem.Repositories.OwnerRepository
import com.example.hotelreservationsystem.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private  val ownerRepository: OwnerRepository):ViewModel() {


    //  getting the live data value
    val ownerResponseLiveData:LiveData<NetworkResult<OwnerResponse>>
        get() = ownerRepository.ownerResponseLiveData


    fun registerOwner(ownerRequest: OwnerRequest) {
        viewModelScope.launch {
            ownerRepository.registerOwner(ownerRequest)
        }
    }

    fun loginOwner(ownerRequest: OwnerRequest) {
        viewModelScope.launch {
            ownerRepository.loginOwner(ownerRequest)
        }

    }

    fun validateCredential(ownerName:String, ownerEmailAddress:String, ownerPassword:String, isOwnerLoginFragment:Boolean):Pair<Boolean,String>
    {
     var result = Pair(true,"")

        if( !isOwnerLoginFragment && TextUtils.isEmpty(ownerName)  || TextUtils.isEmpty(ownerEmailAddress)  || TextUtils.isEmpty(ownerPassword))
        {
            result = Pair(false,"Please provide the credential")
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(ownerEmailAddress).matches())
        {
            result = Pair(false,"Please enter Valid EmailAddress")
        }

        else if(ownerPassword.length  <= 6)
        {
             result = Pair(false,"Password length should be grater than 6")
        }
        return  result

    }
}




