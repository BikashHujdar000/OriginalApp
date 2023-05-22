package com.example.hotelreservationsystem.Fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.hotelreservationsystem.Models.OwnerRequest
import com.example.hotelreservationsystem.Models.UserRequest
import com.example.hotelreservationsystem.R
import com.example.hotelreservationsystem.UserActivity
import com.example.hotelreservationsystem.ViewModels.AuthViewModel
import com.example.hotelreservationsystem.ViewModels.UserAuthViewModel
import com.example.hotelreservationsystem.databinding.FragmentOwnerLoginBinding
import com.example.hotelreservationsystem.databinding.FragmentUserLoginBinding
import com.example.hotelreservationsystem.utils.NetworkResult
import com.example.hotelreservationsystem.utils.TokenManager
import com.example.hotelreservationsystem.utils.constants
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UserLoginFragment : Fragment() {
    lateinit var binding: FragmentUserLoginBinding
    private val userAuthViewModel by viewModels<UserAuthViewModel>()
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


        binding.signIn.setOnClickListener { val validationResult = validateUserInput()
            if (validationResult.first) {

                userAuthViewModel.loginUser(getUserInput())

            } else {
                Toast.makeText(requireContext(), validationResult.second, Toast.LENGTH_SHORT)
                    .show()
            }

        }

        bindObserver()

        binding.forgotPassword.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_userLoginFragment_to_forgotPasswordFragment);
        }
        binding.signUpNow.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_userLoginFragment_to_userRegisterFragment)
        }

    }
    private fun bindObserver() {

        userAuthViewModel.userResponseLiveData.observe(viewLifecycleOwner, Observer {

            binding.progressBar.isVisible = false
            when (it) {
                is NetworkResult.Success -> {

                    tokenManager.saveToken(it.data!!.token)
                    Log.d(constants.TAG,it.data.token)
                    findNavController().navigate(R.id.action_userLoginFragment_to_userHomeFragment);
                }

                is NetworkResult.Error -> {
                    Toast.makeText(requireContext(), "${it.message}", Toast.LENGTH_SHORT).show()
                }

                is NetworkResult.Loading -> {
                    binding.progressBar.isVisible = true

                }

            }

        })
    }

    private fun validateUserInput(): Pair<Boolean, String> {
        val userInput = getUserInput()

        return  userAuthViewModel.validateCredential(userInput.username,userInput.email,userInput.password,true)


    }

    private fun getUserInput(): UserRequest {
        var userEmailAddress = binding.userEmail.text.toString()
        var userPassword = binding.userPassword.text.toString()
        return UserRequest(userEmailAddress, userPassword,"")
    }


}