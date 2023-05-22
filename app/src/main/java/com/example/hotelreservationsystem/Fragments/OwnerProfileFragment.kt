package com.example.hotelreservationsystem.Fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.whenResumed
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.hotelreservationsystem.Models.HotelRequest
import com.example.hotelreservationsystem.Models.Owner
import com.example.hotelreservationsystem.Models.OwnerResponse
import com.example.hotelreservationsystem.R
import com.example.hotelreservationsystem.ViewModels.AuthViewModel
import com.example.hotelreservationsystem.ViewModels.HotelViewModel
import com.example.hotelreservationsystem.databinding.FragmentBookNowBinding
import com.example.hotelreservationsystem.databinding.FragmentOwnerProfileBinding
import com.example.hotelreservationsystem.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Exception
@AndroidEntryPoint

class OwnerProfileFragment : Fragment() {

    lateinit var binding: FragmentOwnerProfileBinding

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



        // want to get owner id over here now


        binding.addhotel.setOnClickListener() {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, 3)
        }

        binding.addhotel.setOnClickListener()

        {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, 4)
        }

        binding
            .updateHotel.setOnClickListener {

                try {
                    hotelViewModel.createHotel(
                        "646af3f7af578d1470b81ecd",
                        HotelRequest(
                            "Trojan Hotel BIkash ",
                            "Bikash Test",
                            "this is one of the Hotel  in the asia "
                        )
                    )
                } catch (e: Exception) {
                    Toast.makeText(requireContext(), "${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
                bindObservers()
               

        binding.updateHotel.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_ownerProfileFragment_to_ownerHomeFragment)
            }


        return binding.root
    }


    private fun bindObservers() {
        hotelViewModel.statusLiveData.observe( viewLifecycleOwner, Observer {
            when(it) {
                is NetworkResult.Success -> {
                    findNavController().popBackStack()
                    Toast.makeText(
                        requireContext(),
                        "originally got the result",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is NetworkResult.Error -> {

                }


                is  NetworkResult.Loading->{

                }

            }


        })

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && data != null) {
            if (requestCode == 3) {
                val selectedimage: Uri? = data.data;
                binding.image1.setImageURI(selectedimage);
            }


        }

            }


        }



