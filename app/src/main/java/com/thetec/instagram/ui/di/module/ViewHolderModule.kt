package com.thetec.instagram.ui.di.module

import androidx.lifecycle.LifecycleRegistry
import com.thetec.instagram.ui.di.ViewHolderScope
import com.thetec.instagram.ui.ui.base.BaseItemViewHolder
import dagger.Module
import dagger.Provides

@Module
class ViewHolderModule(private val viewHolder : BaseItemViewHolder<*,*>) {

    @Provides
    @ViewHolderScope
    fun provideLifecycleRegistery():LifecycleRegistry=LifecycleRegistry(viewHolder)

}