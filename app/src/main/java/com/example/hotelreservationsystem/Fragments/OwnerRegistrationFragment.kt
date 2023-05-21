package com.example.hotelreservationsystem.Fragments

import android.os.Bundle
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
import com.example.hotelreservationsystem.R
import com.example.hotelreservationsystem.ViewModels.AuthViewModel
import com.example.hotelreservationsystem.databinding.FragmentOwnerRegistrationBinding
import com.example.hotelreservationsystem.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Exception

@AndroidEntryPoint
class OwnerRegistrationFragment : Fragment() {
    lateinit var binding: FragmentOwnerRegistrationBinding
    private val authViewModel by viewModels<AuthViewModel> ()

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
            var ownerPassword = binding.editPassword.text.toString();

            if (binding.checkBox.isChecked) {

             authViewModel.registerOwner(OwnerRequest(ownerEmail, ownerName,ownerPassword))
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        authViewModel.ownerResponseLiveData.observe(viewLifecycleOwner, Observer {
            binding.progressBar.isVisible=false
            when(it)
            {
                is NetworkResult.Success->{
                    // code for token
                    findNavController().navigate(R.id.action_ownerRegistrationFragment_to_ownerLoginFragment);

                }
                is NetworkResult.Error->{
                    Toast.makeText(requireContext(), "${it.message}", Toast.LENGTH_SHORT).show()
                }

                is NetworkResult.Loading->{
                    //write code for progress bar
                    binding.progressBar.isVisible= true
                }
            }
        })
    }
}