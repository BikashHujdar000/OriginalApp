package com.example.hotelreservationsystem.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotelreservationsystem.Models.HotelResponseListMethod
import com.example.hotelreservationsystem.Repositories.GetAllRepository
import com.example.hotelreservationsystem.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GetAllHotelViewModel @Inject constructor(  val getAllRepository: GetAllRepository):ViewModel() {
     val _hotelLiveDataList : LiveData<NetworkResult<HotelResponseListMethod>>
        get() = getAllRepository.hotelLiveDataList

     fun getAllHotel(userId: String) {
         viewModelScope.launch {
             getAllRepository.getAllHotel(userId)
         }

    }
}
