package com.example.hotelreservationsystem.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hotelreservationsystem.Adapters.RoomsAdapter
import com.example.hotelreservationsystem.R
import com.example.hotelreservationsystem.databinding.FragmentViewRoomBinding

class ViewRoomFragment : Fragment() {
    lateinit var binding : FragmentViewRoomBinding
    private val args by navArgs<ViewRoomFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentViewRoomBinding.inflate(layoutInflater,container, false)

        //reclerView item setting section
        val rooms = args.roomsAvilable.rooms
        val recyclerView = binding.userViewRoomsRecyclerView
        val roomsAdapter = RoomsAdapter(requireContext(),rooms)
        recyclerView.adapter =roomsAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        return binding.root

    }
}