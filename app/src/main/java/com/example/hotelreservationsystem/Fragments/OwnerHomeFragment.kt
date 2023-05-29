package com.example.hotelreservationsystem.Fragments

import android.os.Binder
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.hotelreservationsystem.Models.OwnerResponse
import com.example.hotelreservationsystem.R
import com.example.hotelreservationsystem.ViewModels.AuthViewModel
import com.example.hotelreservationsystem.databinding.FragmentOwnerHomeBinding
import com.example.hotelreservationsystem.utils.constants.TAG
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Exception
@AndroidEntryPoint
class OwnerHomeFragment : Fragment() {
    lateinit var  binding :FragmentOwnerHomeBinding
    var hotelId:String? = null
    var ownerId :String? = null

   private val authViewModel by viewModels<AuthViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOwnerHomeBinding.inflate(layoutInflater,container,false);
        // setting ups Image Slider

        val arguments = arguments
            val hotelIdFromDirectLogin = arguments!!.getString("hotelIdFromDirect")
            val ownerIdFromDirectLogin = arguments.getString("ownerIdFromDirect")


            val hotelIdFromCreateHotel = arguments.getString("hotelIdFromCreateFragment")
            val ownerIdFromCreateFragment = arguments.getString("ownerIdFromCreateFragment")


         if(hotelIdFromDirectLogin != null && ownerIdFromDirectLogin != null)
         {
             ownerId = ownerIdFromDirectLogin
             hotelId = hotelIdFromDirectLogin

         }
        else if(hotelIdFromCreateHotel != null && ownerIdFromCreateFragment != null)
         {
             hotelId = hotelIdFromCreateHotel
             ownerId = ownerIdFromCreateFragment
         }
        else
         {
             Log.d(TAG,"Something Went wrong on getting ids ")
         }

        val FinalOwnerId = ownerId
        val FinalHotelId = hotelId
        Log.d(TAG,"Final OwnerId  is  $FinalOwnerId ")
        Log.d(TAG,"Final hotel Id  is  $FinalHotelId ")


        val imageList = ArrayList<SlideModel>() // Create image list
         // on image url later please pass the original images of hotel View

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
                findNavController().navigate(
                    R.id.action_ownerHomeFragment_to_addRoomFragment,
                    Bundle().apply {
                        putString("ownerId", ownerId)
                        putString("hotelId", hotelId) })
        }





        binding.roomsList.setOnClickListener{
            findNavController().navigate(
                R.id.action_ownerHomeFragment_to_ownerRoomsFragment,
                Bundle().apply {
                    putString("ownerId", ownerId)
                    putString("hotelId", hotelId)})

        }




//        binding.hotelProfile.setOnClickListener(){
//
//                    findNavController().navigate(R.id.action_ownerHomeFragment_to_ownerProfileFragment,Bundle().apply
//                    {
//                        putString("userId",ownerId)
//                    })
//                }


              binding.bookings.setOnClickListener(){

                  findNavController().navigate(R.id.action_ownerHomeFragment_to_ownersBookingFragment,Bundle().apply {
                      putString("ownerId", ownerId)
                  })
        }


    }


    }

