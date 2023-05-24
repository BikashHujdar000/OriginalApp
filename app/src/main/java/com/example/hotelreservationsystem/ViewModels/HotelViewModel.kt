package com.example.hotelreservationsystem.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotelreservationsystem.Models.HotelRequest
import com.example.hotelreservationsystem.Models.HotelResponse
import com.example.hotelreservationsystem.Repositories.HotelRepositories
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

@HiltViewModel
class HotelViewModel@Inject constructor (private val hotelRepositories: HotelRepositories):ViewModel() {


    val hotelLiveData get() = hotelRepositories.hotelLiveData
    val statusLiveData get() = hotelRepositories.statusLiveData


    fun createHotel(ownerId: String, hotelRequest: HotelRequest) {
        viewModelScope.launch {
            hotelRepositories.createHotel(ownerId, hotelRequest)
        }

    }

}


