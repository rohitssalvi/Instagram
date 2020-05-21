package com.thetec.instagram.ui.di.component

import com.thetec.instagram.ui.di.ActivityScope
import com.thetec.instagram.ui.di.module.ActivityModule
import com.thetec.instagram.ui.ui.authentication.login.Login
import com.thetec.instagram.ui.ui.authentication.register.Register
import com.thetec.instagram.ui.ui.main.MainActivity
import com.thetec.instagram.ui.ui.splash.SplashActivity
import dagger.Component
@ActivityScope
@Component(dependencies = [ApplicationComponent::class],modules = [ActivityModule::class])
interface ActivityComponent {

   fun inject(activity:SplashActivity)

   fun inject(activity:Login)

   fun inject(activity: Register)

   fun inject(activity: MainActivity)

}