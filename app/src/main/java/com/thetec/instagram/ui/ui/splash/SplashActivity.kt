package com.thetec.instagram.ui.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.Observer
import com.thetec.instagram.R
import com.thetec.instagram.ui.di.component.ActivityComponent
import com.thetec.instagram.ui.ui.authentication.login.Login
import com.thetec.instagram.ui.ui.authentication.register.Register
import com.thetec.instagram.ui.ui.base.BaseActivity
import com.thetec.instagram.ui.ui.main.MainActivity
import com.thetec.instagram.ui.utils.display.Toaster

class SplashActivity :BaseActivity<SplashViewModel>()
{
    override fun provideLaoutID(): Int = R.layout.activity_splash

    override fun setupView(savebundle: Bundle?) {

    }

    override fun injectDependency(activityComponent: ActivityComponent) {
        activityComponent.inject(this@SplashActivity)
    }

    override fun setupObserver() {
        super.setupObserver()
        viewModel.lauchActionLoggedIn.observe(this, Observer {
            if(it)
            {
                Handler().postDelayed({
              startActivity(Intent(this,MainActivity::class.java))
              finish() },3000)
            }else
            {
              Handler().postDelayed({
                  startActivity(Intent(this,Register::class.java))
                  finish()
              },3000)

            }

        })

    }


}