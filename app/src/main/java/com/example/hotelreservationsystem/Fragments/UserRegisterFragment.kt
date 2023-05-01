package com.example.hotelreservationsystem.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.hotelreservationsystem.R
import com.example.hotelreservationsystem.databinding.FragmentUserRegisterBinding


class UserRegisterFragment : Fragment() {
    lateinit var binding : FragmentUserRegisterBinding;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUserRegisterBinding.inflate(layoutInflater,container,false);
        binding.signIn.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_userRegisterFragment_to_userLoginFragment);
        }
        binding.signUpButton.setOnClickListener {

            //all credentials are checked here.

            Navigation.findNavController(it).navigate(R.id.action_userRegisterFragment_to_userHomeFragment);
        }
        return binding.root;
    }

}