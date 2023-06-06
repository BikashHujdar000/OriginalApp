package com.example.hotelreservationsystem.Fragments

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.hotelreservationsystem.Adapters.RecomenderAdapter
import com.example.hotelreservationsystem.Adapters.ReviewsAdapterTest
import com.example.hotelreservationsystem.Models.RecommendationRequest
import com.example.hotelreservationsystem.R
import com.example.hotelreservationsystem.TestModels.ReviewModel
import com.example.hotelreservationsystem.ViewModels.AuthViewModel
import com.example.hotelreservationsystem.ViewModels.HotelViewModel
import com.example.hotelreservationsystem.ViewModels.okayTestViewModel
import com.example.hotelreservationsystem.databinding.FragmentOnTouchUserBinding
import com.example.hotelreservationsystem.utils.NetworkResult
import com.example.hotelreservationsystem.utils.constants.Tag2
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Exception

@AndroidEntryPoint
class OnTouchUserFragment : Fragment() {

    var userId: String? = null
    var ownerId: String? = null
    var hotelId: String? = null
    var price: Int? = null
    var type: Int? = null
    var rating: Int? = null

    //{
    //    "price":300,
    //    "type":2,
    //    "rating":4
    //}

    private val okayTestViewModel by viewModels<okayTestViewModel>()
    private val ownerViewModel by viewModels<AuthViewModel>()
    lateinit var binding: com.example.hotelreservationsystem.databinding.FragmentOnTouchUserBinding
    private val args by navArgs<OnTouchUserFragmentArgs>()


    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOnTouchUserBinding.inflate(layoutInflater, container, false);

        binding.recomenderShimmer.startShimmer()
        // something that should be done first
        // setting the hotel name and description with the hotel name from safe argument comming on click action
        binding.hotelName.text = args.hotel.name
        binding.hotelDescription.text = args.hotel.description
        userId = args.userId
        ownerId = args.hotel.owner.toString()
        hotelId = args.hotel._id
        val floatRate = args.hotel.rating.toFloat()
        binding.ratingbarOnTouch.rating = floatRate
        binding.hotelAddressOnTouch.text = args.hotel.address
        val imageAPiList: List<String> = args.hotel.photos
        val imageList = ArrayList<SlideModel>() // Create image list

        for (imageUrl in imageAPiList) {

            imageList.add(SlideModel(imageUrl, ScaleTypes.FIT))
        }

        binding.imageSlider.setImageList(imageList, ScaleTypes.FIT) // for all images
        binding.imageSlider.setImageList(imageList)


        Log.d(Tag2, " okay this is the respionse of single hotel from user home fragment ${args}")

        // okay ma yaha try gardee xu aab
        try {
            price = Integer.parseInt(args.hotel.cheapestPrice.toString())
            type = Integer.parseInt(args.hotel.type.toString())
            rating = Integer.parseInt(args.hotel.rating.toString())
            Log.d(
                Tag2,
                "When i get the data to give the input i get these price $price type $type rating $rating"
            )
            okayTestViewModel.getList(RecommendationRequest(price!!, rating!!, type!!))


        } catch (e: Exception) {
            Log.d(Tag2, "Error on calling the api hit for another response ")
        }
        // kaam yaha baat suru hunxa hai yadi naya  base url call granu xa vaney


        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        binding.availableRooms.setOnClickListener {
            val hotel = args.hotel
            val userId = args.userId
            val action = OnTouchUserFragmentDirections.actionOnTouchUserFragmentToViewRoomFragment(
                hotel,
                userId
            )

            findNavController().navigate(action)

        }


        // setiing for review recycler view


        val ManualReviewData = ArrayList<ReviewModel>()
        ManualReviewData.add(
            ReviewModel(
                "BIkash Hujdar",
                "2076/12/3",
                R.drawable.profiletest,
                "They were extremely accommodating and allowed us to check in early at like 10am. We got to hotel super early and I didn’t wanna wait. So this was a big plus. The sevice was exceptional as well. Would definitely send a friend there.",
                4
            )
        )
        ManualReviewData.add(
            ReviewModel(
                "BIkash Hujdar",
                "2076/12/3",
                R.drawable.profiletest,
                "This is the Best Hotel in the asia",
                4
            )
        )
        ManualReviewData.add(
            ReviewModel(
                "BIkash Hujdar",
                "2076/12/3",
                R.drawable.profiletest,
                "TThey were extremely accommodating and allowed us to check in early at like 10am. We got to hotel super early and I didn’t wanna wait. So this was a big plus. The sevice was exceptional as well. Would definitely send a friend there.",
                4
            )
        )
        ManualReviewData.add(
            ReviewModel(
                "BIkash Hujdar",
                "2076/12/3",
                R.drawable.profiletest,
                "This is the Best Hotel in the asia",
                4
            )
        )
        ManualReviewData.add(
            ReviewModel(
                "BIkash Hujdar",
                "2076/12/3",
                R.drawable.profiletest,
                "This is the Best Hotel in the asia",
                4
            )
        )
        ManualReviewData.add(
            ReviewModel(
                "BIkash Hujdar",
                "2076/12/3",
                R.drawable.profiletest,
                "This is the Best Hotel in the asia",
                4
            )
        )
        ManualReviewData.add(
            ReviewModel(
                "BIkash Hujdar",
                "2076/12/3",
                R.drawable.profiletest,
                "This is the Best Hotel in the asia",
                4
            )
        )
        val reviewAdapter = ReviewsAdapterTest(requireContext(), ManualReviewData)
        val reviewRecycleview = binding.reviewRecyclerView
        reviewRecycleview.adapter = reviewAdapter
        reviewRecycleview.layoutManager = LinearLayoutManager(requireContext());

        okayTestViewModel.recoomendationLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is NetworkResult.Success -> {
                    val ModelData = it.data?.hotels
                    // so now i am changing the array list into list

                    val recommendedAdapter =
                        ModelData?.let { it1 -> RecomenderAdapter(requireContext(), it1) }
                    // if i want to set the recycler view for recommendation system
                    val recomenderrecycleView = binding.recomenderRecyclerView
                    binding.recomenderShimmer.stopShimmer()
                    binding.recomenderShimmer.visibility = View.INVISIBLE
                    recomenderrecycleView.visibility = View.VISIBLE
                    recomenderrecycleView.adapter = recommendedAdapter
                    recomenderrecycleView.layoutManager =
                        LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)


                    // setiign up for recomendation click lisstner

                    if (recommendedAdapter != null) {
                        recommendedAdapter.setOnItemClickListner(object :
                            RecomenderAdapter.onItemClickListner {
                            override fun onItemClick(position: Int) {

                                ownerId = it.data.hotels.get(position).owner.toString()
                                hotelId = it.data.hotels.get(position)._id

                                Log.d(Tag2, "Clicked on item of Recoomender Recycler ")
                                Log.d(Tag2, "Clicked on item of Recoomender Recycler $ownerId ")
                                Log.d(Tag2, "Clicked on item of Recoomender Recycler $hotelId")

                                try {
                                    ownerViewModel.getRecommenderResponse(ownerId!!, hotelId!!)
                                    ownerViewModel.hotelLiveData.observe(viewLifecycleOwner,
                                        Observer {
                                            when (it) {
                                                is NetworkResult.Success -> {
                                                    val hotel = it.data?.hotel

                                                    Log.d(
                                                        Tag2,
                                                        "aab jun ma pathauna chahaxu response "
                                                    )
                                                    Log.d(Tag2, "$hotel")

                                                    val action =
                                                        OnTouchUserFragmentDirections.actionOnTouchUserFragmentSelf(
                                                            hotel!!,
                                                            userId!!
                                                        )
                                                    findNavController().navigate(action)
                                                }

                                                is NetworkResult.Loading -> {}
                                                is NetworkResult.Error -> {}
                                            }

                                        })
                                } catch (e: Exception) {
                                    Log.d(
                                        Tag2,
                                        "Okay sorry to call this api from live data ${e.message} "
                                    )
                                }


                            }

                        })
                    }
                }

                is NetworkResult.Loading -> {

                }

                is NetworkResult.Error -> {

                }
            }
        })
    }


}