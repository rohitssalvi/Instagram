package com.thetec.instagram.ui.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.thetec.instagram.BuildConfig
import com.thetec.instagram.ui.InstagramApplication
import com.thetec.instagram.ui.data.local.db.DatabaseService
import com.thetec.instagram.ui.data.remote.NetworkService
import com.thetec.instagram.ui.data.remote.Networking
import com.thetec.instagram.ui.di.ApplicationContext
import com.thetec.instagram.ui.di.TempDirectory
import com.thetec.instagram.ui.utils.commen.Constants
import com.thetec.instagram.ui.utils.commen.FileUtils
import com.thetec.instagram.ui.utils.network.NetworkHelper
import com.thetec.instagram.ui.utils.rx.RxschedulerProvider
import com.thetec.instagram.ui.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import java.io.File
import javax.inject.Singleton

@Module
class ApplicationModule(val application: InstagramApplication)
{
    @Singleton
    @Provides
    fun provideApplicationContext(): Application =application

    @Singleton
    @Provides
    @ApplicationContext
    fun provideContext():Context=application


     @Provides
     fun provideCompositeDisposable():CompositeDisposable= CompositeDisposable()

     @Provides
     fun provideSchedulerProvider():SchedulerProvider=RxschedulerProvider()

    @Singleton
    @Provides
    fun provideSharedPreference():SharedPreferences=application.getSharedPreferences(Constants.SHAREDPREFRENCE_NAME,Context.MODE_PRIVATE)

    @Singleton
    @Provides
    fun prodiveDatabaseService():DatabaseService=Room.databaseBuilder(application,DatabaseService::class.java,Constants.DATABASE_NEME).build()


    @Singleton
    @Provides
    fun provideNetworkService():NetworkService=Networking.create(BuildConfig.API_KEY,BuildConfig.BASE_URL,application.cacheDir,10*1024*1024)

    @Singleton
    @Provides
    fun provideNetworkHelper():NetworkHelper=NetworkHelper(application)

    @Singleton
    @Provides
    @TempDirectory
    fun provideDirectory():File=FileUtils.getDirectory(application,"temp")
}