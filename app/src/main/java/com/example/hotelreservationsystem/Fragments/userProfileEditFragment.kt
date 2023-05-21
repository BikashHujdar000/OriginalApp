package com.example.hotelreservationsystem.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hotelreservationsystem.R
import com.example.hotelreservationsystem.databinding.FragmentUserProfileEditBinding


class userProfileEditFragment : Fragment() {

    lateinit var binding:FragmentUserProfileEditBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentUserProfileEditBinding.inflate(layoutInflater,container,false)
        binding.updateUser.setOnClickListener {
            val fragement = UserProfileFragment()
            val transaction = fragmentManager?.beginTransaction()
            if (transaction != null) {
                transaction.replace(R.id.fragmentContainerView2,fragement)
                transaction.commit()

            }
        }
        return binding.root
    }
}
