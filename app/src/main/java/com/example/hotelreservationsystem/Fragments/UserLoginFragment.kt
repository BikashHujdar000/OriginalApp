package com.example.hotelreservationsystem.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.hotelreservationsystem.R
import com.example.hotelreservationsystem.UserActivity
import com.example.hotelreservationsystem.databinding.FragmentOwnerLoginBinding
import com.example.hotelreservationsystem.databinding.FragmentUserLoginBinding


class UserLoginFragment : Fragment() {
    lateinit var binding: FragmentUserLoginBinding;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = FragmentUserLoginBinding.inflate(layoutInflater, container,false);
        binding.signIn.setOnClickListener {


            // get the content of the credentials checked here.

            // type 1
            val intent = Intent (getActivity(),UserActivity::class.java)

            getActivity()?.startActivity(intent)


//            Navigation.findNavController(it).navigate(R.id.action_userLoginFragment_to_userHomeFragment);
        }
        binding.forgotPassword.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_userLoginFragment_to_forgotPasswordFragment);
        }
        return binding.root


    }
}