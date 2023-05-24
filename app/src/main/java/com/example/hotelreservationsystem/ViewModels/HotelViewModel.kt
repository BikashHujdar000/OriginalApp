package com.example.hotelreservationsystem.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotelreservationsystem.Models.HotelRequest
<<<<<<< HEAD
import com.example.hotelreservationsystem.Models.RoomRequest
=======
import com.example.hotelreservationsystem.Models.HotelResponse
>>>>>>> refs/remotes/origin/master
import com.example.hotelreservationsystem.Repositories.HotelRepositories
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

@HiltViewModel
class HotelViewModel@Inject constructor (private val hotelRepositories: HotelRepositories):ViewModel() {


<<<<<<< HEAD
    val hotelLiveData  get()= hotelRepositories.hotelLiveData


    val statusLiveData get()= hotelRepositories.statusLiveData
=======
    val hotelLiveData get() = hotelRepositories.hotelLiveData
    val statusLiveData get() = hotelRepositories.statusLiveData
>>>>>>> refs/remotes/origin/master


    fun createHotel(ownerId: String, hotelRequest: HotelRequest) {
        viewModelScope.launch {
            hotelRepositories.createHotel(ownerId, hotelRequest)
        }

    }

<<<<<<< HEAD
    fun addRoom(ownerId: String,hotelId:String,roomRequest: RoomRequest)
    {
        viewModelScope.launch {
            hotelRepositories.addRoom(ownerId,hotelId,roomRequest)
        }

    }

    fun getAllRooms(ownerId: String,hotelId: String)
    {
        viewModelScope.launch {
            hotelRepositories.getAllRoom(ownerId,hotelId)
        }
    }
    fun deleteRoom(ownerId: String,hotelId: String,roomId:String)
    {
        viewModelScope.launch {
            hotelRepositories.deleteRoom(ownerId,hotelId,roomId)
        }
    }


}
=======
}


>>>>>>> refs/remotes/origin/master
