package com.thetec.instagram.ui.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.thetec.instagram.ui.InstagramApplication
import com.thetec.instagram.ui.di.component.DaggerFragmentComponent
import com.thetec.instagram.ui.di.component.DaggerViewHolderComponent
import com.thetec.instagram.ui.di.component.FragmentComponent
import com.thetec.instagram.ui.di.component.ViewHolderComponent
import com.thetec.instagram.ui.di.module.FragmentModule
import com.thetec.instagram.ui.di.module.ViewHolderModule
import com.thetec.instagram.ui.utils.display.Toaster
import javax.inject.Inject

abstract class BaseItemViewHolder<T: Any,VM: BaseItemViewModel<T>>(
    @LayoutRes layoudId: Int, parent: ViewGroup
) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(layoudId,parent,false)), LifecycleOwner
{
    override fun getLifecycle(): Lifecycle = lifecycleRegistry

   init {
       onCreate()
   }

    @Inject
    lateinit var viewModel:VM

    @Inject
    lateinit var lifecycleRegistry: LifecycleRegistry

    open fun bind(data:T)
    {
        viewModel.updateData(data)
    }

    protected fun onCreate()
    {
        injectDependency(dependencyInjectViewHolder())
        lifecycleRegistry.currentState=Lifecycle.State.INITIALIZED
        lifecycleRegistry.currentState=Lifecycle.State.CREATED
        setupObserver()
        setupView(itemView)
    }

     fun onStatrt()
    {
        lifecycleRegistry.currentState=Lifecycle.State.STARTED
        lifecycleRegistry.currentState=Lifecycle.State.RESUMED
    }

    fun onStop()
    {
        lifecycleRegistry.currentState=Lifecycle.State.STARTED
        lifecycleRegistry.currentState=Lifecycle.State.CREATED
    }

    fun onDestroyed()
    {
        lifecycleRegistry.currentState=Lifecycle.State.DESTROYED
    }
    protected open fun setupObserver()
    {
        viewModel.stringMessageId.observe(this, Observer {

            showMessage(it)
        })

        viewModel.stringMessage.observe(this, Observer {

            showMessage(it)
        })
    }


    fun showMessage(message:String)= Toaster.showToast(itemView.context,message)

    fun showMessage(@StringRes resId:Int)=showMessage(itemView.context.getString(resId))

    private  fun dependencyInjectViewHolder(): ViewHolderComponent {
        return DaggerViewHolderComponent
            .builder()
            .applicationComponent((itemView.context.applicationContext as InstagramApplication).applicationComponent)
            .viewHolderModule(ViewHolderModule(this))
            .build()

    }

    protected abstract fun setupView(view: View?)

    protected abstract fun injectDependency(viewHolderComponent: ViewHolderComponent)

}