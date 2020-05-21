package com.thetec.instagram.ui.di.component

import android.content.Context
import androidx.fragment.app.Fragment
import com.thetec.instagram.ui.di.ActivityContext
import com.thetec.instagram.ui.di.FragmentScope
import com.thetec.instagram.ui.di.ViewHolderScope
import com.thetec.instagram.ui.di.module.ApplicationModule
import com.thetec.instagram.ui.di.module.FragmentModule
import com.thetec.instagram.ui.di.module.ViewHolderModule
import com.thetec.instagram.ui.ui.home.post.PostItemViewHolder
import com.thetec.instagram.ui.ui.home.post.PostItemViewModel
import dagger.Component
@ViewHolderScope
@Component(dependencies = [ApplicationComponent::class],modules = [ViewHolderModule::class])
interface ViewHolderComponent {
   fun inject(viewHolder : PostItemViewHolder)
}