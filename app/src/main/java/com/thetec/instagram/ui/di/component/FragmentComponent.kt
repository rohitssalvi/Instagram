package com.thetec.instagram.ui.di.component

import android.content.Context
import androidx.fragment.app.Fragment
import com.thetec.instagram.ui.di.ActivityContext
import com.thetec.instagram.ui.di.FragmentScope
import com.thetec.instagram.ui.di.module.ApplicationModule
import com.thetec.instagram.ui.di.module.FragmentModule
import com.thetec.instagram.ui.ui.home.HomeFragment
import com.thetec.instagram.ui.ui.photo.PhotoFragment
import com.thetec.instagram.ui.ui.profile.ProfileFragment
import dagger.Component
@FragmentScope
@Component(dependencies = [ApplicationComponent::class],modules = [FragmentModule::class])
interface FragmentComponent {

    fun inject(fragment : HomeFragment)
    fun inject(fragment : PhotoFragment)
    fun inject(fragment : ProfileFragment)


}