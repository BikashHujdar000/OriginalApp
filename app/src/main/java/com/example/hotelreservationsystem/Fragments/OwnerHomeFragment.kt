package com.example.hotelreservationsystem.Fragments

import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.hotelreservationsystem.R
import com.example.hotelreservationsystem.databinding.FragmentOwnerHomeBinding

class OwnerHomeFragment : Fragment() {
    lateinit var  binding :FragmentOwnerHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOwnerHomeBinding.inflate(layoutInflater,container,false);


        // setting ups Image Slider

        val imageList = ArrayList<SlideModel>() // Create image list
         // on image url later please pass the original images of hotel Views



        imageList.add(SlideModel(R.drawable.tst,scaleType = ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.tst1,scaleType = ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.tst2,scaleType = ScaleTypes.FIT))

        binding. imageSlider.setImageList(imageList, ScaleTypes.FIT) // for all images
        binding.imageSlider.setImageList(imageList)



        binding.addRooms.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_ownerHomeFragment_to_addRoomFragment);
        }


        return binding.root;
    }



}