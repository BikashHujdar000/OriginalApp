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
import androidx.navigation.Navigation
import com.example.hotelreservationsystem.R
import com.example.hotelreservationsystem.databinding.FragmentBookNowBinding
import com.example.hotelreservationsystem.databinding.FragmentOwnerProfileBinding


class OwnerProfileFragment : Fragment() {
    lateinit var binding: FragmentOwnerProfileBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOwnerProfileBinding.inflate(
            layoutInflater, container, false
        )
        // Inflate the layout for this fragment


        binding.addhotelImage.setOnClickListener() {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, 3)
        }
        binding
            .updateHotel.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_ownerProfileFragment_to_ownerHomeFragment)
            }
        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && data != null) {
            if (requestCode == 3) {
                val selectedimage: Uri? = data.data;
                binding.hotelimage.setImageURI(selectedimage);
            }


        }
    }
}