package com.example.hotelreservationsystem.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.hotelreservationsystem.R
import com.example.hotelreservationsystem.databinding.FragmentOtpConfirmationBinding
import com.example.hotelreservationsystem.databinding.FragmentOwnerOtpConfirmationBinding

class OtpConfirmationFragment : Fragment() {

    lateinit var binding : FragmentOtpConfirmationBinding;


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOtpConfirmationBinding.inflate(layoutInflater,container,false);
        binding.update.setOnClickListener {

            // updating password  is in this field
            Navigation.findNavController(it).navigate(R.id.action_otpConfirmationFragment_to_userHomeFragment);
        }
        return binding.root
    }

}