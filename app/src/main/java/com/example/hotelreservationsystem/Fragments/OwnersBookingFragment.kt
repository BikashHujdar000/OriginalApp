package com.example.hotelreservationsystem.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.hotelreservationsystem.Adapters.BookingRoomAdapter
import com.example.hotelreservationsystem.R
import com.example.hotelreservationsystem.ViewModels.HotelViewModel
import com.example.hotelreservationsystem.databinding.FragmentOwnersBookingBinding
import com.example.hotelreservationsystem.utils.NetworkResult
import com.example.hotelreservationsystem.utils.constants.TAG
import dagger.hilt.android.AndroidEntryPoint
import hilt_aggregated_deps._com_example_hotelreservationsystem_ViewModels_AuthViewModel_HiltModules_KeyModule

@AndroidEntryPoint
class OwnersBookingFragment : Fragment() {
    lateinit var binding : FragmentOwnersBookingBinding
    private   val args by navArgs<OwnersBookingFragmentArgs>()

val hotelViewModel by viewModels<HotelViewModel> ()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOwnersBookingBinding.inflate(layoutInflater,container,false)
        val ownerId = args.ownerResponse.owner._id.toString()
        Log.d("argument aayo","$ownerId")
        hotelViewModel.showBooking(ownerId)
        Log.d("apiResponse","Ayoo")


        hotelViewModel.allbookingLiveData.observe(viewLifecycleOwner, Observer {

            when(it){

                is NetworkResult.Success ->{
                    val response = it.data!!.booking
                    Log.d("iopopo","$response")
                  // val bookingAdapter= BookingRoomAdapter(requireContext(),roomList)
                }
                is NetworkResult.Loading ->{}
                is NetworkResult.Error ->{}
                else -> {}
            }

        })
       // val recycler = binding.booikingRecycler

        return binding.root
    }



}