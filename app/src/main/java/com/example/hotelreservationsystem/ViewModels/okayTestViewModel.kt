package com.example.hotelreservationsystem.ViewModels

import android.nfc.Tag
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.hotelreservationsystem.Repositories.OkayTestRepository
import com.example.hotelreservationsystem.utils.constants.Tag2
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class okayTestViewModel @Inject constructor( val okayTestRepository: OkayTestRepository):ViewModel(){

   fun getDetails(){
       Log.d(Tag2,"view model ma xiro call gareko function ")
       viewModelScope.launch {
           okayTestRepository.getDetails()
       }

   }

}

