package com.thetec.instagram.ui.ui.editprofile

import android.os.Bundle
import com.thetec.instagram.R
import com.thetec.instagram.ui.di.component.ActivityComponent
import com.thetec.instagram.ui.ui.base.BaseActivity
import kotlinx.android.synthetic.main.custom_toolbar.*

class EditProfileActivity : BaseActivity<EditProfileViewModel>() {
    override fun provideLaoutID(): Int = R.layout.edit_profile_layout

    override fun setupView(savebundle: Bundle?) {
        iv_back_arrow.setOnClickListener{
            finish()
        }

        im_tick.setOnClickListener{

        }
    }

    override fun injectDependency(activityComponent: ActivityComponent) {

    }
}