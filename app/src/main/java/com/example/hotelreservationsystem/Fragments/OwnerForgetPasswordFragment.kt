package com.example.hotelreservationsystem.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.hotelreservationsystem.R
import com.example.hotelreservationsystem.databinding.FragmentOwnerForgetPasswordBinding

class OwnerForgetPasswordFragment : Fragment() {

lateinit var binding: FragmentOwnerForgetPasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         // Inflate the layout for this fragment
        binding = FragmentOwnerForgetPasswordBinding.inflate(layoutInflater,container,false);
        binding.sendOtp.setOnClickListener {
            val email = binding.emailAddress.text.toString();
            if (email.isNotEmpty()) {
                //validity of the email is performed;

                Navigation.findNavController(it)
                    .navigate(R.id.action_ownerForgetPasswordFragment_to_ownerOtpVerificationFragment);
            }
            binding.backToLogin.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_ownerForgetPasswordFragment_to_ownerLoginFragment);
            }
        }

        return binding.root;
    }


}