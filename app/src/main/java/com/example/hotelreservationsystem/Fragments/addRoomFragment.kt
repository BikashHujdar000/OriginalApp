package com.example.hotelreservationsystem.Fragments

import android.content.Context
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
import androidx.navigation.Navigation
import com.example.hotelreservationsystem.R
import com.example.hotelreservationsystem.databinding.FragmentAddRoomBinding
import com.example.hotelreservationsystem.utils.constants.TAG
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream

class addRoomFragment : Fragment() {

    lateinit var  imageUri: Uri
    private val contract= registerForActivityResult(ActivityResultContracts.GetContent()){
        imageUri = it!!
        binding.image1.setImageURI(it)

        try {
            upload()
        }catch (
            e:Exception
        )
        {
            Log.d(TAG,e.message.toString())
        }
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
           Navigation.findNavController(it).navigate(R.id.action_addRoomFragment_to_ownerHomeFragment)
}

        // Inflate the layout for this fragment
        return binding.root
    }


   private  fun upload()
   {
       val filesDir =requireContext().filesDir
       val file = File(filesDir,"image.png")
       val resolver = context?.contentResolver
       val inputStream = resolver?.openInputStream(imageUri)
       val outputStream = FileOutputStream(file)
       inputStream!!.copyTo(outputStream)


       val requestBody= file.asRequestBody("image/*".toMediaTypeOrNull())
       val part = MultipartBody.Part.createFormData("profile",file.name,requestBody)
       Log.d(TAG,part.toString())

       // profile vanekko filed ho  postman ko
       // now retrofit parameter image ko k ho ta part
//       // @Multipart
//       @Post("endpoint")
//       suspend fun uploadimage(
//           @Part image :MultipartBody.Part
//       ):: response Item
   }



}