package com.example.hotelreservationsystem.Fragments

import android.net.Network
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hotelreservationsystem.Adapters.RoomsAdapter
import com.example.hotelreservationsystem.Adapters.TestAdapters
import com.example.hotelreservationsystem.Models.Room
import com.example.hotelreservationsystem.R
import com.example.hotelreservationsystem.ViewModels.HotelViewModel
import com.example.hotelreservationsystem.databinding.FragmentOwnerRoomsBinding
import com.example.hotelreservationsystem.databinding.FragmentUpdateRoomBinding
import com.example.hotelreservationsystem.utils.NetworkResult
import com.example.hotelreservationsystem.utils.constants.TAG
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Exception
import kotlin.math.log
@AndroidEntryPoint
class OwnerRoomsFragment : Fragment() {

    lateinit var binding: FragmentOwnerRoomsBinding
    var ownerId: String? = null
    var hoteid: String? = null



//    private  val args by navArgs<OwnerRoomsFragmentArgs>()


    private val hotelViewModel by viewModels<HotelViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOwnerRoomsBinding.inflate(layoutInflater, container, false)

        // getting the owner id  and hotel id
        ownerId = requireArguments().getString("ownerId").toString()
        hoteid = requireArguments().getString("hotelId").toString()

        Log.d(TAG, "ownerid and hotelId  $ownerId $hoteid")

        // data should be
        //  646e22b095405e6d962cc2cb  and 646e25f9b2a982f41b6e6519

        hotelViewModel.getAllRooms(ownerId!!, hoteid!!)
            hotelViewModel.hotelLiveData.observe(viewLifecycleOwner, Observer {
                when (it) {
                    is NetworkResult.Loading -> {

                    }

                    is NetworkResult.Success -> {


                        Log.d(TAG, "  data is generated")
                        val roomData: List<Room> = it.data?.hotel!!.rooms

                        Log.d(TAG, " Room Lisy ois $roomData")
                        try {
                            val recyclerView = binding.roomViewRecyclerView
                            val roomAdapter = RoomsAdapter(requireContext(),it.data.hotel.rooms)
                            recyclerView.adapter = roomAdapter
                            recyclerView.layoutManager = LinearLayoutManager(requireContext())
                            roomAdapter.notifyDataSetChanged()



//                                //setting up adapters in recycleviewlistner
//        adapters.setOnItemClickListner(
//            object :TestAdapters.onItemClickListner
//            {
//                @SuppressLint("SuspiciousIndentation")
//                override fun onItemClick(position: Int) {
//
//                val action = UserHomeFragmentDirections.actionUserHomeFragment2ToOnTouchUserFragment()
//                    findNavController().navigate(action)
//
//                }
//
//            } )
                            roomAdapter.setOnItemClickListner(
                                object :RoomsAdapter.onItemClickListner
                                {
                                    override fun onItemClick(position: Int) {
                                        val roomDetails = it.data.hotel.rooms.get(position)

                                         val action = OwnerRoomsFragmentDirections.actionOwnerRoomsFragmentToUpdateRoomFragment()
                                        findNavController().navigate(action)
                                    }

                                }
                            )

                        } catch (e: Exception) {
                            Log.d(TAG, " eroor on adapting recyclerview  ${e.message}")
                        }


                    }

                    is NetworkResult.Error -> {

                    }

                }
            })

        return binding.root
    }
}
