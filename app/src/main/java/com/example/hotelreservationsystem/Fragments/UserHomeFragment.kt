package com.example.hotelreservationsystem.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hotelreservationsystem.Adapters.RecomenderAdapter
import com.example.hotelreservationsystem.Adapters.TestAdapters
import com.example.hotelreservationsystem.TestModels.DataModel
import com.example.hotelreservationsystem.databinding.FragmentUserHomeBinding


class UserHomeFragment : Fragment() {
    lateinit var binding:FragmentUserHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentUserHomeBinding.inflate(layoutInflater,container,false)
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


        // if i want to set the recycler view for recommendation system
        val recomenderrecycleView = binding.recomenderRecyclerView
        recomenderrecycleView.adapter = recommendedAdapter
        recomenderrecycleView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)

        // setiing up recycler View for  hotels all
        recyclerView.adapter=adapters
        recyclerView.layoutManager= LinearLayoutManager(requireContext())


        // chanfging abovve code after api
        // Inflate the layout for this fragment

        return binding.root
    }

}