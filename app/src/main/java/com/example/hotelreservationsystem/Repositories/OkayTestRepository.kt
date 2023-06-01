package com.example.hotelreservationsystem.Repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hotelreservationsystem.Models.RecommendationRequest
import com.example.hotelreservationsystem.Models.RecoomenderResponse
import com.example.hotelreservationsystem.api.okayTestApio
import com.example.hotelreservationsystem.utils.NetworkResult
import com.example.hotelreservationsystem.utils.constants.Tag2
import java.lang.Exception
import javax.inject.Inject

class OkayTestRepository @Inject constructor(private val okayTestApio: okayTestApio) {
    private val _recommendationListLiveData = MutableLiveData<NetworkResult<RecoomenderResponse>>()
        val recomenderLiveData :LiveData<NetworkResult<RecoomenderResponse>>
            get() = _recommendationListLiveData
suspend  fun getList(recommendationRequest: RecommendationRequest)
{
    _recommendationListLiveData.postValue(NetworkResult.Loading())
    try {
        val response = okayTestApio.getList(recommendationRequest)
        if (response.isSuccessful && response.body()!= null)
        {
            Log.d(Tag2,"okay Response from ml model Generated ")
            Log.d(Tag2,"${response.body().toString()}")
            _recommendationListLiveData.postValue(NetworkResult.Success(response.body()!!))
        }
        else
        {
            _recommendationListLiveData.postValue(NetworkResult.Error("Something Went Wrong"))
        }
    }
    catch (e:Exception)
    {
        Log.d(Tag2,"Okay  This is error to call api ${e.message.toString()}")
    }

}




}