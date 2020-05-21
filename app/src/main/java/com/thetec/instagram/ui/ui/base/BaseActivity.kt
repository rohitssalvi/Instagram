package com.thetec.instagram.ui.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.thetec.instagram.ui.InstagramApplication
import com.thetec.instagram.ui.di.component.ActivityComponent
import com.thetec.instagram.ui.di.component.DaggerActivityComponent
import com.thetec.instagram.ui.di.module.ActivityModule
import com.thetec.instagram.ui.utils.display.Toaster
import javax.inject.Inject

abstract class BaseActivity<vm : BaseViewModel> :AppCompatActivity() {

    @Inject
    lateinit var viewModel: vm

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependency(dependencyInject())
        super.onCreate(savedInstanceState)
        setContentView(provideLaoutID())
        setupObserver()
        viewModel.onCreate()
        setupView(savedInstanceState)
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

     private fun dependencyInject():ActivityComponent
     {
        return DaggerActivityComponent.builder().applicationComponent((application as InstagramApplication).applicationComponent)
            .activityModule(ActivityModule(this))
            .build()
     }

    fun showMessage(message:String)=Toaster.showToast(applicationContext,message)

    fun showMessage(@StringRes resId:Int)=showMessage(getString(resId))


    @LayoutRes
    protected abstract fun provideLaoutID():Int

    protected abstract fun setupView(savebundle:Bundle?)

    protected abstract fun injectDependency(activityComponent: ActivityComponent)



}