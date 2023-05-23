package com.example.hotelreservationsystem.Fragments

import android.os.Binder
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.hotelreservationsystem.Models.OwnerResponse
import com.example.hotelreservationsystem.R
import com.example.hotelreservationsystem.databinding.FragmentOwnerHomeBinding
import com.example.hotelreservationsystem.utils.constants.TAG
import com.google.gson.Gson
import java.lang.Exception

class OwnerHomeFragment : Fragment() {
    lateinit var  binding :FragmentOwnerHomeBinding



 private val args by navArgs<OwnerHomeFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOwnerHomeBinding.inflate(layoutInflater,container,false);

//        binding.hotelName.text = args.ownerResponse.owner._id
        // setting ups Image Slider

        val imageList = ArrayList<SlideModel>() // Create image list
         // on image url later please pass the original images of hotel Views



        imageList.add(SlideModel(R.drawable.tst,scaleType = ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.tst1,scaleType = ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.tst2,scaleType = ScaleTypes.FIT))

        binding. imageSlider.setImageList(imageList, ScaleTypes.FIT) // for all images
        binding.imageSlider.setImageList(imageList)





        return binding.root;
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addRooms.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_ownerHomeFragment_to_addRoomFragment);

        }
        binding.roomsList.setOnClickListener(){
            Navigation.findNavController(it).navigate(R.id.action_ownerHomeFragment_to_ownerRoomsFragment)
        }
        binding.hotelProfile.setOnClickListener(){
            try {
                 val userId = args.ownerResponse.owner._id
                findNavController().navigate(R.id.action_ownerHomeFragment_to_ownerProfileFragment,Bundle().apply {
                    putString("userId",userId)
                })

            }catch (
                e:Exception
            ){

            }


        }
        binding.bookings.setOnClickListener(){
            Navigation.findNavController(it).navigate(R.id.action_ownerHomeFragment_to_ownersBookingFragment)
        }

    }



}