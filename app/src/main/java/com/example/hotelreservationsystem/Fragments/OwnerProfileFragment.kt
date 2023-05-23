package com.example.hotelreservationsystem.Fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.hotelreservationsystem.Models.HotelRequest

import com.example.hotelreservationsystem.R
import com.example.hotelreservationsystem.ViewModels.AuthViewModel
import com.example.hotelreservationsystem.ViewModels.HotelViewModel

import com.example.hotelreservationsystem.databinding.FragmentOwnerProfileBinding
import com.example.hotelreservationsystem.utils.NetworkResult
import com.example.hotelreservationsystem.utils.constants.TAG
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Exception
@AndroidEntryPoint

class OwnerProfileFragment : Fragment() {

    lateinit var binding: FragmentOwnerProfileBinding
    var ownerId:String ? = null

    private var selectedImageri: Uri?= null
    private val authViewModel by viewModels<AuthViewModel> ()
    private val hotelViewModel by viewModels<HotelViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOwnerProfileBinding.inflate(
            layoutInflater, container, false
        )
        // Inflate the layout for this fragment

        ///acessing the sent owner id from the data
        ownerId =  requireArguments().getString("userId").toString()
        binding.toptext.text = ownerId


//        binding.addImage1.setOnClickListener() {
//
//            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
//            startActivityForResult(intent, 3)
//        }


        binding
            .updateHotel.setOnClickListener {

                var name:String= binding.hotelName.text.toString()
                var addresses:String = binding.hotelLocation.text.toString()
                var description:String = binding.hotelDescription.text.toString()
                try {
                    hotelViewModel.createHotel(ownerId!!,HotelRequest(name,addresses,description)

                    )
                } catch (e: Exception) {
                    Toast.makeText(requireContext(), "${e.message}", Toast.LENGTH_SHORT).show()
                }

            }
        bindObservers()

        return binding.root
    }

    private fun bindObservers() {
        hotelViewModel.statusLiveData.observe( viewLifecycleOwner, Observer {
            binding.progressBar.isVisible= false
            when(it) {
                is NetworkResult.Success -> {

                 Log.d(TAG,"Hotel Created Sucessfully")
                    findNavController().popBackStack()
                }

                is NetworkResult.Error -> {
                    Log.d(TAG,it.message.toString())

                }

                is  NetworkResult.Loading->{
                    binding.progressBar.isVisible = true

                }

            }


        })

    }


//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (resultCode == Activity.RESULT_OK && data != null) {
//            if (requestCode == 3) {
//                val selectedimage: Uri? = data.data;
//                binding.image1.setImageURI(selectedimage);
//            }
//
//
//        }
//
//    }


}

