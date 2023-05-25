package com.example.hotelreservationsystem

import ScrollHandler
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import com.example.hotelreservationsystem.Fragments.UserBookingFragment
import com.example.hotelreservationsystem.Fragments.UserHomeFragment
import com.example.hotelreservationsystem.Fragments.UserProfileFragment
import com.example.hotelreservationsystem.databinding.ActivityUserBinding


class UserActivity : AppCompatActivity() {
    lateinit var  binding: ActivityUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       binding = ActivityUserBinding.inflate(layoutInflater)


        setContentView(binding.root)
        // setiing the status baar and icon

        val window =this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)
        WindowCompat.getInsetsController(window, window.decorView).apply {
            isAppearanceLightStatusBars = true
        }
        val bottomBar = binding.bottomNavigationBar
        val layoutParams = bottomBar .layoutParams as CoordinatorLayout.LayoutParams
        layoutParams.behavior = ScrollHandler()
        bottomBar.setOnItemSelectedListener {
            when(it)
            {
                0->{

                    val  fragment= UserBookingFragment()
                    bottomBar.visibility=View.GONE
                    switchFragment(fragment)


                }
                1->{

                    val fragment = UserHomeFragment()
                    switchFragment(fragment)

                }
                2->{

                    val fragment = UserProfileFragment()
                    switchFragment(fragment)

                }

            }
        }





    }
    private fun switchFragment(fragment: Fragment) {
        var  fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView2, fragment)
        fragmentTransaction.commit()
    }






}
