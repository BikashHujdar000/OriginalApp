package com.example.hotelreservationsystem.Fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Binder
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.denzcoskun.imageslider.models.SlideModel
import com.example.hotelreservationsystem.R
import com.example.hotelreservationsystem.databinding.FragmentAddRoomBinding
import java.net.URI

class addRoomFragment : Fragment() {
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
    binding.addImage1.setOnClickListener() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, 3)
    }


        binding.addImage2.setOnClickListener() {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, 4)
        }


        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && data != null)
        {
            if( requestCode == 3)
            {
                val selectedimage: Uri? = data.data;
                binding.image1.setImageURI(selectedimage);
            }
            else if(requestCode == 4)
            {
                val selectedimage: Uri? = data.data;
                binding.image2.setImageURI(selectedimage);

            }



        }
    }

}