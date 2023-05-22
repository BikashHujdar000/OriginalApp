package com.example.hotelreservationsystem.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.hotelreservationsystem.R
import com.example.hotelreservationsystem.databinding.FragmentUserLoginBinding
import com.example.hotelreservationsystem.utils.TokenManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UserLoginFragment : Fragment() {
    lateinit var binding: FragmentUserLoginBinding


@Inject
 lateinit var tokenManager : TokenManager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = FragmentUserLoginBinding.inflate(layoutInflater, container, false);
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.signIn.setOnClickListener {


        }


        binding.forgotPassword.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_userLoginFragment_to_forgotPasswordFragment);
        }
        binding.signUpNow.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_userLoginFragment_to_userRegisterFragment)
        }

    }


}



