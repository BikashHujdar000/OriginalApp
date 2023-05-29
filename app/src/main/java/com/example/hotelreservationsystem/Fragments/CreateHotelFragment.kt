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
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.hotelreservationsystem.Models.HotelRequest
import com.example.hotelreservationsystem.R
import com.example.hotelreservationsystem.ViewModels.AuthViewModel
import com.example.hotelreservationsystem.ViewModels.HotelViewModel
import com.example.hotelreservationsystem.databinding.FragmentCreateHotelBinding
import com.example.hotelreservationsystem.databinding.FragmentOwnerProfileBinding
import com.example.hotelreservationsystem.utils.NetworkResult
import com.example.hotelreservationsystem.utils.constants
import com.example.hotelreservationsystem.utils.constants.TAG
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream

@AndroidEntryPoint
class CreateHotelFragment : Fragment() {
    lateinit var binding :FragmentCreateHotelBinding

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

        Log.d(constants.TAG, imageUri.toString())
        Log.d(constants.TAG, "when call from the hotel create Fragment ${part.toString()}")

        authViewModel.uploadImage(part)
        authViewModel.photoResonseLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is NetworkResult.Success -> {
                    try {
                        Log.d(constants.TAG, "Show me the image uri  of  hotel images ${it.data?.url}")
                        imagePath = it.data!!.url
                        Log.d(constants.TAG, "k xa ta image path ma  $imagePath")
                        this.context?.let { it1 -> Glide.with(it1).load(imageUri).into(binding.image1) }

                        binding.createHotel.visibility = View.VISIBLE

                    } catch (e: Exception) {
                    }
                    Log.d(constants.TAG, "Image path Getting Error")
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
        // Inflate the layout for this fragment
        binding = FragmentCreateHotelBinding.inflate(layoutInflater,container,false)


        // kaam yaha baat
         // hotel id pathaunu paro bass hain ta kin vaney owner id pahiley dekhin kee xa ma sanga


        //acessing the sent owner id from the data

        ownerId = requireArguments().getString("ownerId").toString()
        Log.d(TAG,"Owner id  is $ownerId")

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
                    Log.d(constants.TAG, "Hotel Created Sucessfully")
                    Log.d(TAG," hotel Response ${it.data?.hotel}")
                    val hotelIdFromCreate = it.data?.hotel?._id
                    val ownerIdFromCreate = it.data?.hotel?.owner?._id
                    Log.d(TAG,"create home fragment ko id $hotelIdFromCreate")

                    //  /  val owner = OwnerResponse(it.data!!.access_token.toString(),it.data.owner)
                      findNavController().navigate(R.id.action_createHotelFragment_to_ownerHomeFragment,Bundle().apply {
                          putString("hotelIdFromCreateFragment",hotelIdFromCreate)
                          putString("ownerIdFromCreateFragment",ownerIdFromCreate)
                      })

                }

                is NetworkResult.Loading -> {

                }

                is NetworkResult.Error -> {

                }
            }
        })



        return binding.root
    }



}