package com.example.hotelreservationsystem.Fragments

import android.net.Uri
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.hotelreservationsystem.Models.HotelRequest
import com.example.hotelreservationsystem.Models.HotelResponse
import com.example.hotelreservationsystem.Models.OwnerResponse
import com.example.hotelreservationsystem.R
import com.example.hotelreservationsystem.ViewModels.AuthViewModel
import com.example.hotelreservationsystem.ViewModels.HotelViewModel
import com.example.hotelreservationsystem.databinding.FragmentOwnerProfileBinding
import com.example.hotelreservationsystem.utils.NetworkResult
import com.example.hotelreservationsystem.utils.constants.TAG
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream

import kotlin.Exception


@AndroidEntryPoint

class OwnerProfileFragment : Fragment() {

    lateinit var binding: FragmentOwnerProfileBinding
    var ownerId: String? = null

    lateinit var imageUri: Uri
    lateinit var imagePath: String

    private val authViewModel by viewModels<AuthViewModel>()
    private val hotelViewModel by viewModels<HotelViewModel>()


    private val contract = registerForActivityResult(ActivityResultContracts.GetContent()) {
        imageUri = it!!
//        binding.image1.setImageURI(it)

        // converting the image
        val filesDir = requireContext().filesDir
        val file = File(filesDir, "image.png")
        val resolver = context?.contentResolver
        val inputStream = resolver?.openInputStream(imageUri)
        val outputStream = FileOutputStream(file)
        inputStream!!.copyTo(outputStream)

        val requestBody = file.asRequestBody("image/*".toMediaTypeOrNull())
        val part = MultipartBody.Part.createFormData("photos", file.name, requestBody)

        Log.d(TAG, imageUri.toString())
        Log.d(TAG, "when call from the hotel create Fragment ${part.toString()}")

        authViewModel.uploadImage(part)
        authViewModel.photoResonseLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is NetworkResult.Success -> {
                    try {
                        Log.d(TAG, "Show me the image uri  of  hotel images ${it.data?.url}")
                        imagePath = it.data!!.url
                        Log.d(TAG, "k xa ta image path ma  $imagePath")
                        this.context?.let { it1 -> Glide.with(it1).load(imageUri).into(binding.image1) }

                    } catch (e: Exception) {
                    }
                    Log.d(TAG, "Image path Getting Error")
                }

                is NetworkResult.Error -> {
                }

                is NetworkResult.Loading -> {

                }
            }

        })


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOwnerProfileBinding.inflate(
            layoutInflater, container, false
        )
        // Inflate the layout for this fragment

        //acessing the sent owner id from the data

        ownerId = requireArguments().getString("userId").toString()


        binding.toptext.text = "UserName"
        binding.addImage1.setOnClickListener {
            contract.launch("image/*")

        }



        binding.createHotel.setOnClickListener {

            var name: String = binding.hotelName.text.toString()
            var addresses: String = binding.hotelLocation.text.toString()
            var description: String = binding.hotelDescription.text.toString()
            var image: String = imagePath.toString()
            try {
                hotelViewModel.createHotel(
                    ownerId!!, HotelRequest(name, addresses, description, image)
                )
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "${e.message}", Toast.LENGTH_SHORT).show()
            }

        }
        hotelViewModel.hotelLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is NetworkResult.Success -> {

                    val hotelId = it.data?.hotel?._id
                    Log.d(TAG, "hotel baneko id k ho tan $hotelId")
                    Log.d(TAG, "Hotel Created Sucessfully")

                    val owner = it.data?.hotel?.owner
                    // error handling  the error
                    Log.d(TAG,"Owner response  $owner")
                    //  /  val owner = OwnerResponse(it.data!!.access_token.toString(),it.data.owner)
                    findNavController().popBackStack()
                }

                is NetworkResult.Loading -> {

                }

                is NetworkResult.Error -> {

                }
            }
        })
//        bindObservers()

        return binding.root
    }

//    private fun bindObservers() {
//        hotelViewModel.statusLiveData.observe( viewLifecycleOwner, Observer {
//            binding.progressBar.isVisible= false
//            when(it) {
//                is NetworkResult.Success -> {
//
//                 Log.d(TAG,"Hotel Created Sucessfully")
//                    findNavController().popBackStack()
//                }
//
//                is NetworkResult.Error -> {
//                    //Log.d(TAG,it.message.toString())
//                }
//                is  NetworkResult.Loading->{
//                    binding.progressBar.isVisible = true
//
//                }
//
//            }
//
//
//        })
//
//    }


}

