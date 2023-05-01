package com.example.hotelreservationsystem.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.hotelreservationsystem.R
import com.example.hotelreservationsystem.databinding.FragmentOwnerRegistrationBinding


class OwnerRegistrationFragment : Fragment() {
    lateinit var binding: FragmentOwnerRegistrationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOwnerRegistrationBinding.inflate(layoutInflater,container,false);
        binding.signIn.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_ownerRegistrationFragment_to_ownerLoginFragment)
        }
        binding.signUpButton.setOnClickListener {
            // Navigate to the owner home fragment with the data.
            var ownerName = binding.editName.text.toString();
            var ownerEmail = binding.editEmail.text.toString();
            var ownerPhoneNumber = binding.editPhone.text.toString();
            var ownerPassword = binding.editPassword.text.toString();
            Navigation.findNavController(it).navigate(R.id.action_ownerRegistrationFragment_to_ownerHomeFragment);
        }
        // Inflate the layout for this fragment
        return binding.root
    }

}