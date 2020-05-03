package com.thetec.instagram.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.thetec.instagram.R
import com.thetec.instagram.ui.main.HomeActivity

class SplashActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            val intent=Intent(this@SplashActivity,HomeActivity::class.java)
            startActivity(intent)
        },2000)

    }
}