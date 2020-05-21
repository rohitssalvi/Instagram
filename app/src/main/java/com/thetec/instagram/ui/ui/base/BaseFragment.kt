package com.thetec.instagram.ui.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.thetec.instagram.ui.InstagramApplication
import com.thetec.instagram.ui.di.component.DaggerFragmentComponent
import com.thetec.instagram.ui.di.component.FragmentComponent
import com.thetec.instagram.ui.di.module.FragmentModule
import com.thetec.instagram.ui.utils.display.Toaster
import javax.inject.Inject

abstract class BaseFragment<vm : BaseViewModel> : Fragment() {
    @Inject
    lateinit var viewModel:vm

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependency(dependencyInjectFragment())
        super.onCreate(savedInstanceState)
        setupObserver()
        viewModel.onCreate()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(provideLaoutID(),container,false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView(view,savedInstanceState)

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


    fun showMessage(message:String)= Toaster.showToast(context!!.applicationContext,message)

    fun showMessage(@StringRes resId:Int)=showMessage(getString(resId))

    private  fun dependencyInjectFragment():FragmentComponent{
        return DaggerFragmentComponent
            .builder()
            .applicationComponent((context!!.applicationContext as InstagramApplication).applicationComponent)
            .fragmentModule(FragmentModule(this))
            .build()

    }
    @LayoutRes
    protected abstract fun provideLaoutID():Int

    protected abstract fun setupView(viw:View?,savebundle:Bundle?)

    protected abstract fun injectDependency(fragmentComponent: FragmentComponent)



}