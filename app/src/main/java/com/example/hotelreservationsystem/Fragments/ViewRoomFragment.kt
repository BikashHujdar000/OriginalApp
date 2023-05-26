package com.example.hotelreservationsystem.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hotelreservationsystem.Adapters.AvailableRoomsAdapter
import com.example.hotelreservationsystem.Adapters.RoomsAdapter
import com.example.hotelreservationsystem.R
import com.example.hotelreservationsystem.databinding.FragmentViewRoomBinding
import com.example.hotelreservationsystem.utils.constants.TAG

class ViewRoomFragment : Fragment() {
    lateinit var binding : FragmentViewRoomBinding
 private  val args by navArgs<ViewRoomFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

       val rooms= args.hotel.rooms
        Log.d(TAG," Room datas are $rooms")
        // Inflate the layout for this fragment
        binding = FragmentViewRoomBinding.inflate(layoutInflater,container, false)
        //reclerView item setting section

        val recyclerView = binding.userViewRoomsRecyclerView
        val roomsAdapter = AvailableRoomsAdapter(requireContext(),rooms)
        recyclerView.adapter =roomsAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        return binding.root

    }
}