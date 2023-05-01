package com.example.hotelreservationsystem.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.hotelreservationsystem.R
import com.example.hotelreservationsystem.databinding.FragmentOtpVerificationBinding
import com.example.hotelreservationsystem.databinding.FragmentOwnerOtpVerificationBinding


class OtpVerificationFragment : Fragment() {
    lateinit var binding : FragmentOtpVerificationBinding;
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOtpVerificationBinding.inflate(layoutInflater,container,false);
        binding.continueBtn.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_otpVerificationFragment_to_otpConfirmationFragment);
        }
        return binding.root;
    }


}