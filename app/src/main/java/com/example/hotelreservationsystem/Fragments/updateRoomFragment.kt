package com.example.hotelreservationsystem.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hotelreservationsystem.R
import com.example.hotelreservationsystem.databinding.FragmentUpdateRoomBinding
class updateRoomFragment : Fragment() {
    lateinit var  binding: FragmentUpdateRoomBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding  = FragmentUpdateRoomBinding.inflate(layoutInflater,container,false)


        // Work  upder this fragment




        return binding.root
    }

}