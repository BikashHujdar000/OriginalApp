package com.example.hotelreservationsystem.Repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hotelreservationsystem.Models.TestModelResponse
import com.example.hotelreservationsystem.api.okayTestApio
import com.example.hotelreservationsystem.utils.NetworkResult
import com.example.hotelreservationsystem.utils.constants.Tag2
import javax.inject.Inject

class OkayTestRepository @Inject constructor(private val okayTestApio: okayTestApio) {

    private val _okayTestLivedata  =MutableLiveData<NetworkResult<TestModelResponse>>()
    val okayTestLiveData :LiveData<NetworkResult<TestModelResponse>>
        get() =  _okayTestLivedata

    suspend fun getDetails(){

        val response = okayTestApio.getDetails()
        if(response.isSuccessful){

            Log.d(Tag2,"response is being generated from the 2 nd base url ${response.body().toString()}")
        }
    }

}
//class GetAllRepository @Inject constructor( private val getallHotelsApi: getallHotelsApi)