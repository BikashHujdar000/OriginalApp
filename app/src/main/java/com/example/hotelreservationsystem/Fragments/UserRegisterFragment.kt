package com.example.hotelreservationsystem.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.hotelreservationsystem.R
import com.example.hotelreservationsystem.databinding.FragmentUserRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserRegisterFragment : Fragment() {
    lateinit var binding: FragmentUserRegisterBinding;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUserRegisterBinding.inflate(layoutInflater, container, false);


        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.signIn.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_userRegisterFragment_to_userLoginFragment);
        }

        //on clicking signUp button in user choice

        binding.signUpButton.setOnClickListener {

            if (binding.checkBox.isChecked) {



            }
        }


    }
}




