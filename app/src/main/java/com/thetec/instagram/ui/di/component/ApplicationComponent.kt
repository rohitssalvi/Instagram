package com.thetec.instagram.ui.di.component

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.thetec.instagram.ui.InstagramApplication
import com.thetec.instagram.ui.data.local.db.DatabaseService
import com.thetec.instagram.ui.data.remote.NetworkService
import com.thetec.instagram.ui.data.repository.PostRepository
import com.thetec.instagram.ui.data.repository.UserRepository
import com.thetec.instagram.ui.di.ApplicationContext
import com.thetec.instagram.ui.di.TempDirectory
import com.thetec.instagram.ui.di.module.ApplicationModule
import com.thetec.instagram.ui.utils.network.NetworkHelper
import com.thetec.instagram.ui.utils.rx.SchedulerProvider
import dagger.Component
import io.reactivex.disposables.CompositeDisposable
import java.io.File
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
  fun inject(application : InstagramApplication);

  fun getApplication(): Application
  @ApplicationContext
  fun getContext():Context

  fun getCompositeDisposable():CompositeDisposable
  fun getSchedulerProvider():SchedulerProvider
  fun getSharedPreference():SharedPreferences
  fun getDatabaseService():DatabaseService
  fun getNetworkService():NetworkService
  fun getNetworkHelper():NetworkHelper

  fun getUserRepository():UserRepository
  @TempDirectory
  fun getDirectory():File




}