package com.example.hotelreservationsystem.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import com.example.hotelreservationsystem.R
import com.example.hotelreservationsystem.databinding.FragmentChooseBinding


class chooseFragment : Fragment() {
    lateinit var binding: FragmentChooseBinding
    lateinit var user: TextView;
    lateinit var owner:TextView;
    lateinit var navigation : Navigation;
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChooseBinding.inflate(layoutInflater, container, false)
        binding.user.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_chooseFragment_to_userRegisterFragment);
        }
        binding.hotelOwner.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_chooseFragment_to_ownerRegistrationFragment);
        }
        return binding.root



    }




}