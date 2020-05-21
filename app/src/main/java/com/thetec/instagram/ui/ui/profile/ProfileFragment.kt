package com.thetec.instagram.ui.ui.profile

import android.os.Bundle
import android.view.View
import com.thetec.instagram.R
import com.thetec.instagram.ui.di.component.FragmentComponent
import com.thetec.instagram.ui.ui.base.BaseFragment

class ProfileFragment : BaseFragment<ProfileViewModel>() {


    companion object{
        const val TAG="ProfileFragment"

        fun newInstance():ProfileFragment{
            val arg=Bundle()
            val profileFragment=ProfileFragment()
            profileFragment.arguments=arg
            return profileFragment
        }
    }

    override fun provideLaoutID(): Int = R.layout.fragment_profile

    override fun setupView(viw: View?, savebundle: Bundle?) {

    }

    override fun injectDependency(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)

    }
}