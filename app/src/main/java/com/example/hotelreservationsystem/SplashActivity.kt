package com.example.hotelreservationsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // setting up post delayed in lap
        Handler(Looper.getMainLooper()).postDelayed(
            Runnable {
                startActivity(Intent(this,MainActivity::class.java))
            },1000
        )
    }

}