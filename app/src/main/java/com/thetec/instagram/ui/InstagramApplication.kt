package com.thetec.instagram.ui

import android.app.Application
import androidx.multidex.MultiDexApplication
import com.thetec.instagram.ui.di.component.ApplicationComponent
import com.thetec.instagram.ui.di.component.DaggerApplicationComponent
import com.thetec.instagram.ui.di.module.ApplicationModule
import javax.inject.Inject

class InstagramApplication : MultiDexApplication() {

    lateinit var applicationComponent: ApplicationComponent
    override fun onCreate() {
        super.onCreate()
        injectDependency();
    }

     fun injectDependency() {
        applicationComponent=DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build();
         applicationComponent.inject(this)
    }
}