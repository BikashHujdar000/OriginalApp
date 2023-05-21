package com.example.hotelreservationsystem.Fragments

import android.os.Build
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.hotelreservationsystem.R
import com.example.hotelreservationsystem.databinding.FragmentUserProfileBinding
import me.ibrahimsn.lib.SmoothBottomBar


class UserProfileFragment : Fragment() {
    lateinit var binding  : FragmentUserProfileBinding

    lateinit var profileExpandable: ConstraintLayout

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUserProfileBinding.inflate(layoutInflater, container, false)
        var bottom = activity?.requireViewById<SmoothBottomBar>(R.id.bottom_navigation_bar)
        if (bottom != null) {
            bottom.visibility = View.GONE
        }

        // setting the dropdown function
        profileExpandable = binding.expandableView

        binding.profileDropdown.setOnClickListener {
            if (profileExpandable.visibility == View.GONE) {
                TransitionManager.beginDelayedTransition(binding.cardView, AutoTransition())
                profileExpandable.visibility = View.VISIBLE
                binding.profileDropdown.setImageResource(R.drawable.arrow_up)

            } else {
                TransitionManager.beginDelayedTransition(binding.cardView, AutoTransition())
                profileExpandable.visibility = View.GONE
                binding.profileDropdown.setImageResource(R.drawable.ic_baseline_arrow_drop_down_24)
                //
            }
        }
        binding.helpAndSupport.setOnClickListener {

            if (binding.helpAndSupportConstraint.visibility == View.GONE) {
                TransitionManager.beginDelayedTransition(binding.cardView, AutoTransition())
                binding.helpAndSupportConstraint.visibility = View.VISIBLE
            } else {
                TransitionManager.beginDelayedTransition(binding.cardView, AutoTransition())
                binding.helpAndSupportConstraint.visibility = View.GONE
            }
        }
        binding.privacytab.setOnClickListener {

            if (binding.securityAndPrivacyConstraint.visibility == View.GONE) {
                TransitionManager.beginDelayedTransition(binding.cardView, AutoTransition())
                binding.securityAndPrivacyConstraint.visibility = View.VISIBLE
            } else {
                TransitionManager.beginDelayedTransition(binding.cardView, AutoTransition())
                binding.securityAndPrivacyConstraint.visibility = View.GONE
            }
        }

        //trying to access the card view menber
        binding.editProfile.setOnClickListener {
            val fragement = UserProfileFragment()
            val transaction = fragmentManager?.beginTransaction()
            if (transaction != null) {
                transaction.replace(R.id.fragmentContainerView2, fragement)
                transaction.commit()
            }

        }
        binding.profileIcon.setOnClickListener {
            val fragement =  UserProfileFragment()
            val transaction = fragmentManager?.beginTransaction()
            if (transaction != null) {
                transaction.replace(R.id.fragmentContainerView2, fragement)
                transaction.commit()
            }

        }
        binding.info.setOnClickListener {
            val fragement =  UserProfileFragment()
            val transaction = fragmentManager?.beginTransaction()
            if (transaction != null) {
                transaction.replace(R.id.fragmentContainerView2, fragement)
                transaction.commit()
            }

        }



        return binding.root
    }

}

