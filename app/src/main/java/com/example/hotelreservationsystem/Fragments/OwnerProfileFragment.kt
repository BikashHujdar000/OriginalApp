package com.example.hotelreservationsystem.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.hotelreservationsystem.R
import com.example.hotelreservationsystem.databinding.FragmentBookNowBinding
import com.example.hotelreservationsystem.databinding.FragmentOwnerProfileBinding


class OwnerProfileFragment : Fragment() {
    lateinit var  binding: FragmentOwnerProfileBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOwnerProfileBinding.inflate(layoutInflater,container,false
        )
        // Inflate the layout for this fragment



        binding
            .updateHotel.setOnClickListener{
                Navigation.findNavController(it).navigate(R.id.action_ownerProfileFragment_to_ownerHomeFragment)
            }
        return binding.root
    }


}