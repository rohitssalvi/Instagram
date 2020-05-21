package com.thetec.instagram.ui.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.thetec.instagram.R
import com.thetec.instagram.ui.di.component.ActivityComponent
import com.thetec.instagram.ui.ui.base.BaseActivity
import com.thetec.instagram.ui.ui.home.HomeFragment
import com.thetec.instagram.ui.ui.photo.PhotoFragment
import com.thetec.instagram.ui.ui.profile.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_toolbar.*
import javax.inject.Inject

class MainActivity : BaseActivity<MainViewModel>(){
    companion object{
        const val TAG="MainActivity"
    }
    @Inject
    lateinit var mainShareViewModel: MainShareViewModel
    private var activeFragment : Fragment?= null
    override fun provideLaoutID(): Int = R.layout.activity_main

    override fun setupView(savebundle: Bundle?) {

        iv_back_arrow.visibility = View.GONE
        im_tick.visibility = View.GONE
        bottom_nav.run {
            itemIconTintList = null
            setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.home_menu -> {
                        viewModel.homeSelected()
                        true
                    }
                    R.id.photo_menu -> {
                        viewModel.photoSelected()
                        true
                    }
                    R.id.profile_menu -> {

                        viewModel.profileSelected()
                        true
                    }
                    else -> false
                }
            }

        }
    }

    override fun injectDependency(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun setupObserver() {
        super.setupObserver()

        viewModel.homeFragmentShow.observe(this, Observer {
        if(it)
        {
            showHome()
        } })

        viewModel.photoFragmentShow.observe(this, Observer {
            if(it)
            {
                showPhoto()
            }
        })


        viewModel.profileFragmentShow.observe(this, Observer {
            if(it)
            {
                showProfile()
            }
        })

        mainShareViewModel.homeRedirection.observe(this, Observer {
            it.getIfNotHandled()?.run {
                bottom_nav.selectedItemId=R.id.home_menu
            }
        })



    }

    private fun showHome() {
       if(activeFragment is HomeFragment) return

        val fragmentTransation=supportFragmentManager.beginTransaction()

        var fragment= supportFragmentManager.findFragmentByTag(HomeFragment.TAG) as HomeFragment?

        if(fragment==null)
        {
            fragment= HomeFragment.newInstance()
            fragmentTransation.add(R.id.fragment_container,fragment,HomeFragment.TAG)
        }else
        {
           fragmentTransation.show(fragment)
        }

        if(activeFragment!=null) fragmentTransation.hide(activeFragment as Fragment)

        fragmentTransation.commit()

        activeFragment=fragment
    }


    private fun showPhoto() {
        if(activeFragment is PhotoFragment) return

        val fragmentTransation=supportFragmentManager.beginTransaction()

        var fragment= supportFragmentManager.findFragmentByTag(PhotoFragment.TAG) as PhotoFragment?

        if(fragment==null)
        {
            fragment= PhotoFragment.newInstance()
            fragmentTransation.add(R.id.fragment_container,fragment,PhotoFragment.TAG)
        }else
        {
            fragmentTransation.show(fragment)
        }

        if(activeFragment!=null) fragmentTransation.hide(activeFragment as Fragment)

        fragmentTransation.commit()

        activeFragment=fragment
    }


    private fun showProfile() {
        if(activeFragment is ProfileFragment) return

        val fragmentTransation=supportFragmentManager.beginTransaction()

        var fragment= supportFragmentManager.findFragmentByTag(ProfileFragment.TAG) as ProfileFragment?

        if(fragment==null)
        {
            fragment= ProfileFragment.newInstance()
            fragmentTransation.add(R.id.fragment_container,fragment,ProfileFragment.TAG)
        }else
        {
            fragmentTransation.show(fragment)
        }

        if(activeFragment!=null) fragmentTransation.hide(activeFragment as Fragment)

        fragmentTransation.commit()

        activeFragment=fragment
    }


}