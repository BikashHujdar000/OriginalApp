package com.example.hotelreservationsystem.Fragments

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.ui.text.toLowerCase
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hotelreservationsystem.Adapters.TestAdapters
import com.example.hotelreservationsystem.Models.Hotel
import com.example.hotelreservationsystem.Models.HotelResponse
import com.example.hotelreservationsystem.R

import com.example.hotelreservationsystem.ViewModels.GetAllHotelViewModel
import com.example.hotelreservationsystem.databinding.FragmentUserHomeBinding
import com.example.hotelreservationsystem.utils.NetworkResult
import com.example.hotelreservationsystem.utils.constants.TAG
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale
import kotlin.math.log

@AndroidEntryPoint
class UserHomeFragment : Fragment() {
    lateinit var binding: FragmentUserHomeBinding
    private val getAllHotelViewModel by viewModels<GetAllHotelViewModel>()
    private var ownerResponse: HotelResponse? = null
    var userId: String? = null
    var hotelList: ArrayList<Hotel>? = null
    //for the serialized data to handle in this fragment


    @SuppressLint("ClickableViewAccessibility")
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserHomeBinding.inflate(layoutInflater, container, false)

        // applyying shimmer

        binding.userHomeShimmer.startShimmer()
        //setting username in the profile head
//        binding.userName.text = args.user.user.username
        binding.userName.text = requireArguments().getString("username")


        //getting userId from the UserResponse
        userId = requireArguments().getString("userId")


        // yaha dekhi tal matra acess gar hain data
        Log.d(TAG, "user Home Fragment Thiche maile and i get user ID $userId")

        getAllHotelViewModel.getAllHotel(userId!!)

        binding.shapeableImageView2.setOnClickListener {

            findNavController().navigate(
                R.id.action_userHomeFragment_to_userProfileFragment,
                Bundle().apply {
                    putString("userId", userId)
                })

        }

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        try {
            getAllHotelViewModel._hotelLiveDataList.observe(
                viewLifecycleOwner,
                Observer {
                    when (it) {
                        is NetworkResult.Success -> {

                            Log.d(TAG, "response is Sucess ")
                            val response = it.data?.hotel
                            Log.d("response dekha", "aayo la")

                            hotelList = response as ArrayList<Hotel>?
                            val recyclerView = binding.userTestHomeRecycler
                            val hotelAdapters = TestAdapters(requireContext(), hotelList)
                            binding.userHomeShimmer.stopShimmer()
                            binding.userHomeShimmer.visibility = View.INVISIBLE
                            recyclerView.visibility = View.VISIBLE
                            recyclerView.adapter = hotelAdapters

                            recyclerView.layoutManager =
                                LinearLayoutManager(requireContext())
                            Log.d("Hotel Respnose Success", response.toString())


                            //  aab yaha ma lagauxu ssearch View


                            binding.searchHome.setOnQueryTextListener(object :
                                androidx.appcompat.widget.SearchView.OnQueryTextListener {
                                override fun onQueryTextSubmit(query: String?): Boolean {
                                    return false
                                }

                                override fun onQueryTextChange(newText: String?): Boolean {

                                    try {
                                        val newFilterdList = ArrayList<Hotel>()
                                        for (i in hotelList!!) {
                                            if (i.address.toLowerCase(Locale.ROOT)
                                                    .contains(newText!!)
                                            ) {
                                                newFilterdList.add(i)
                                            }
                                        }
                                        hotelAdapters.searchList(newFilterdList)


                                    } catch (e: java.lang.Exception) {
                                        Toast.makeText(
                                            requireContext(),
                                            "Someething Went wrong ",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                    return true
                                }

                            })
                            hotelAdapters.setOnItemClickListner(object :
                                TestAdapters.onItemClickListner {
                                override fun onItemClick(position: Int) {
                                    val hotel = hotelAdapters.data!!.get(position)
                                    Log.d(TAG, " user home dekhi pathauda $hotel")
                                    Log.d(TAG, "  user id kxa $userId")
                                    val action =
                                        UserHomeFragmentDirections.actionUserHomeFragmentToOnTouchUserFragment(
                                            hotel,
                                            userId!!
                                        )
                                    findNavController().navigate(action)


                                }

                            })

                        }

                        is NetworkResult.Error -> {

                        }

                        is NetworkResult.Loading -> {

                        }

                        else -> {}
                    }
                })
        } catch (e: Exception) {
            Log.d(TAG, "hit vayen url")
        }


    }


}