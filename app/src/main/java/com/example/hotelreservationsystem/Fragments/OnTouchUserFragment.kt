package com.example.hotelreservationsystem.Fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.hotelreservationsystem.Adapters.RecomenderAdapter
import com.example.hotelreservationsystem.Adapters.ReviewsAdapterTest
import com.example.hotelreservationsystem.Models.HotelResponse
import com.example.hotelreservationsystem.R
import com.example.hotelreservationsystem.TestModels.DataModel
import com.example.hotelreservationsystem.TestModels.ReviewModel
import com.example.hotelreservationsystem.databinding.FragmentOnTouchUserBinding
import me.ibrahimsn.lib.SmoothBottomBar


class OnTouchUserFragment : Fragment() {
    lateinit var binding:com.example.hotelreservationsystem.databinding.FragmentOnTouchUserBinding


    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnTouchUserBinding.inflate(layoutInflater,container,false);
        // Inflate the layout for this fragment

        // hidding the lower bar



        val imageList = ArrayList<SlideModel>() // Create image list
        imageList.add(SlideModel(R.drawable.tst,scaleType = ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.tst1,scaleType = ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.tst2,scaleType = ScaleTypes.FIT))
        binding. imageSlider.setImageList(imageList, ScaleTypes.FIT)
        binding.imageSlider.setImageList(imageList)

        val manualData = ArrayList<DataModel>()
        val recommendedAdapter = RecomenderAdapter(requireContext(),manualData)
        // if i want to set the recycler view for recommendation system
        val recomenderrecycleView = binding.recomenderRecyclerView
        recomenderrecycleView.adapter = recommendedAdapter
        recomenderrecycleView.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.HORIZONTAL,false)


        // setiing for review recycler view
       val ManualReviewData = ArrayList<ReviewModel>()
        ManualReviewData.add(ReviewModel("BIkash Hujdar","2076/12/3",R.drawable.profiletest,"They were extremely accommodating and allowed us to check in early at like 10am. We got to hotel super early and I didn’t wanna wait. So this was a big plus. The sevice was exceptional as well. Would definitely send a friend there.",4))
        ManualReviewData.add(ReviewModel("BIkash Hujdar","2076/12/3",R.drawable.profiletest,"This is the Best Hotel in the asia",4))
        ManualReviewData.add(ReviewModel("BIkash Hujdar","2076/12/3",R.drawable.profiletest,"TThey were extremely accommodating and allowed us to check in early at like 10am. We got to hotel super early and I didn’t wanna wait. So this was a big plus. The sevice was exceptional as well. Would definitely send a friend there.",4))
        ManualReviewData.add(ReviewModel("BIkash Hujdar","2076/12/3",R.drawable.profiletest,"This is the Best Hotel in the asia",4))
        ManualReviewData.add(ReviewModel("BIkash Hujdar","2076/12/3",R.drawable.profiletest,"This is the Best Hotel in the asia",4))
        ManualReviewData.add(ReviewModel("BIkash Hujdar","2076/12/3",R.drawable.profiletest,"This is the Best Hotel in the asia",4))
        ManualReviewData.add(ReviewModel("BIkash Hujdar","2076/12/3",R.drawable.profiletest,"This is the Best Hotel in the asia",4))
         val reviewAdapter =ReviewsAdapterTest(requireContext(),ManualReviewData)
        val reviewRecycleview = binding.reviewRecyclerView
        reviewRecycleview.adapter = reviewAdapter
        reviewRecycleview.layoutManager = LinearLayoutManager(requireContext());



        return binding.root



    }

}