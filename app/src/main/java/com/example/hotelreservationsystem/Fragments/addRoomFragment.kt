package com.example.hotelreservationsystem.Fragments
import android.net.Network
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.example.hotelreservationsystem.R
import com.example.hotelreservationsystem.ViewModels.AuthViewModel
import com.example.hotelreservationsystem.ViewModels.HotelViewModel
import com.example.hotelreservationsystem.databinding.FragmentAddRoomBinding
import com.example.hotelreservationsystem.utils.NetworkResult
import com.example.hotelreservationsystem.utils.constants.TAG
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.create
import java.io.File
import java.io.FileOutputStream


@AndroidEntryPoint
class addRoomFragment : Fragment() {


    lateinit var  imageUri: Uri
    lateinit var  imagePath:String

    private val hotelViewModel by viewModels<HotelViewModel>()
    private  val authViewModel by viewModels<AuthViewModel> ()


    private val contract= registerForActivityResult(ActivityResultContracts.GetContent()){
        imageUri = it!!
        binding.image1.setImageURI(it)

        // converting the image
        val filesDir =requireContext().filesDir
        val file = File(filesDir,"image.png")
        val resolver = context?.contentResolver
        val inputStream = resolver?.openInputStream(imageUri)
        val outputStream = FileOutputStream(file)
        inputStream!!.copyTo(outputStream)

        val requestBody= file.asRequestBody("image/*".toMediaTypeOrNull())
        val part = MultipartBody.Part.createFormData("photos",file.name,requestBody)

        Log.d(TAG,imageUri.toString())
        Log.d(TAG,part.toString())

            // image converted

        // getting the url of the image

       authViewModel.uploadImage(part)
        authViewModel.photoResonseLiveData.observe(viewLifecycleOwner, Observer {
            when(it){
                 is NetworkResult.Success->
                 {
                     Log.d(TAG,"Show me the image uri ${it.data?.url}")
                     imagePath = it.data!!.url
                 }
                is NetworkResult.Error ->{

                }
                is NetworkResult.Loading->{
                }
            }
        })

    }

    lateinit var binding: FragmentAddRoomBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAddRoomBinding.inflate(layoutInflater, container, false)
        // setting for dropdowns


        val itemsselecor = resources.getStringArray(R.array.selectors);
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.list_item, itemsselecor);
        binding.autocomplete.setAdapter(arrayAdapter)

        binding.autocomplete.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val text = parent.getItemAtPosition(position);

            }

        binding.addImage1.setOnClickListener {
        contract.launch("image/*")
    }

       binding.updateHotel.setOnClickListener(){

       }


        // Inflate the layout for this fragment
        return binding.root
    }







}