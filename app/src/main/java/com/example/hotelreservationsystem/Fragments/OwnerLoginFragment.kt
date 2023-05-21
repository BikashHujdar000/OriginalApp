package com.example.hotelreservationsystem.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.hotelreservationsystem.R
import com.example.hotelreservationsystem.databinding.FragmentOwnerLoginBinding


class OwnerLoginFragment : Fragment() {
    lateinit var binding: FragmentOwnerLoginBinding;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOwnerLoginBinding.inflate(layoutInflater, container,false);
        binding.signIn.setOnClickListener {


            // get the content of the credentials checked here.


            Navigation.findNavController(it).navigate(R.id.action_ownerLoginFragment_to_ownerHomeFragment);
        }
        binding.forgotPassword.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_ownerLoginFragment_to_ownerForgetPasswordFragment)
        }
        binding.signUpNow.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_ownerLoginFragment_to_ownerRegistrationFragment)
        }
        // Inflate the layout for this fragment
        return binding.root
    }


}