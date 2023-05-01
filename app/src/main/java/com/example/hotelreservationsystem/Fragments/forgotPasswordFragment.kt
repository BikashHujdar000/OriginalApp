package com.example.hotelreservationsystem.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.hotelreservationsystem.R
import com.example.hotelreservationsystem.databinding.FragmentForgotPasswordBinding
import com.example.hotelreservationsystem.databinding.FragmentOwnerForgetPasswordBinding


class forgotPasswordFragment : Fragment() {
    lateinit var binding: FragmentForgotPasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentForgotPasswordBinding.inflate(layoutInflater,container,false);
        binding.sendOtp.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_forgotPasswordFragment_to_otpVerificationFragment);
        }
        binding.backToLogin.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_forgotPasswordFragment_to_userLoginFragment);
        }

        return binding.root;
    }


}