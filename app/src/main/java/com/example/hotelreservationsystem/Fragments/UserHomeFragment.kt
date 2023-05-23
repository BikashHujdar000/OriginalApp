package com.example.hotelreservationsystem.Fragments

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hotelreservationsystem.Adapters.RecomenderAdapter
import com.example.hotelreservationsystem.Adapters.TestAdapters
import com.example.hotelreservationsystem.Models.HotelResponse
import com.example.hotelreservationsystem.TestModels.DataModel
import com.example.hotelreservationsystem.databinding.FragmentUserHomeBinding


class UserHomeFragment : Fragment() {
    lateinit var binding:FragmentUserHomeBinding

private  var ownerResponse: HotelResponse?= null
    private val args by navArgs<UserHomeFragmentArgs>()


    @SuppressLint("ClickableViewAccessibility")
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserHomeBinding.inflate(layoutInflater,container,false)
        binding.userName.text = args.user.user.username

     val recyclerView = binding.userTestHomeRecycler

        // serting ups looking for visualization to topic


        val manualData = ArrayList<DataModel>()
     manualData.add(DataModel("Trojan National Hotel","United",1))
        manualData.add(DataModel("Trojan National Hotel","United",1))
        manualData.add(DataModel("sigma National Hotel","United",1))
        manualData.add(DataModel("LojanNational Hotel","United",1))
        manualData.add(DataModel("TarhanNational Hotel","United",1))
        manualData.add(DataModel("Trojan National Hotel","United",1))
        manualData.add(DataModel("famndfk fkanlkd Hotel","United",1))
        manualData.add(DataModel("Trojan National Hotel","United",1))
        manualData.add(DataModel("Sirha National Hotel","United",1))
        manualData.add(DataModel("Trojan National Hotel","United",1))

     val adapters = TestAdapters(requireContext(),manualData);
        val recommendedAdapter = RecomenderAdapter(requireContext(),manualData)

        // setiing up recycler View for  hotels all
        recyclerView.adapter=adapters
        recyclerView.layoutManager= LinearLayoutManager(requireContext())

        //setting up adapters in recycleviewlistner
        adapters.setOnItemClickListner(
            object :TestAdapters.onItemClickListner
            {
                @SuppressLint("SuspiciousIndentation")
                override fun onItemClick(position: Int) {

                val action = UserHomeFragmentDirections.actionUserHomeFragment2ToOnTouchUserFragment()
                    findNavController().navigate(action)

                }

            } )

        // handling search view
        binding.searchItem.setOnTouchListener(OnTouchListener { v, event ->
            val DRAWABLE_LEFT = 0
            val DRAWABLE_TOP = 1
            val DRAWABLE_RIGHT = 2
            val DRAWABLE_BOTTOM = 3
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= binding.searchItem.getRight() - binding.searchItem.getCompoundDrawables()
                        .get(DRAWABLE_RIGHT).getBounds().width()
                ) {
                    // your action here
                    try {
                        var addressname = binding.searchItem.text
                        Toast.makeText(requireContext(), " search is clicked data : ${addressname}", Toast.LENGTH_SHORT).show()
                    }catch (
                        e:Exception
                    )
                    {
                        Toast.makeText(requireContext(), "data Invalid Taken", Toast.LENGTH_SHORT).show()
                    }

                    return@OnTouchListener true
                }
            }
            true
        })



        // chanfging abovve code after api
        // Inflate the layout for this fragment

        return binding.root
    }

}