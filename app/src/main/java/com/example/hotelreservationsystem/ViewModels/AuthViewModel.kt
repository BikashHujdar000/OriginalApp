package com.example.hotelreservationsystem.ViewModels

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

    fun loginOwner(ownerResponse: OwnerResponse) {
        viewModelScope.launch {
            ownerRepository.loginOwner(ownerResponse)

        }
    }
}




