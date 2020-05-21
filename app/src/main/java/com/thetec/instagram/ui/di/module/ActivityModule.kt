package com.thetec.instagram.ui.di.module

import android.app.Activity
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.thetec.instagram.ui.data.repository.UserRepository
import com.thetec.instagram.ui.di.ActivityContext
import com.thetec.instagram.ui.ui.authentication.login.LoginViewModel
import com.thetec.instagram.ui.ui.authentication.register.RegisterViewModel
import com.thetec.instagram.ui.ui.base.BaseActivity
import com.thetec.instagram.ui.ui.main.MainShareViewModel
import com.thetec.instagram.ui.ui.main.MainViewModel
import com.thetec.instagram.ui.ui.splash.SplashViewModel
import com.thetec.instagram.ui.utils.ViewModelProviderFactory
import com.thetec.instagram.ui.utils.network.NetworkHelper
import com.thetec.instagram.ui.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class ActivityModule(private val activity: BaseActivity<*>) {
    @ActivityContext
    @Provides
    fun provideContext():Context=activity

    @Provides
    fun provideSplashViewModel(schedulerProvider: SchedulerProvider,compositeDisposable: CompositeDisposable,networkHelper: NetworkHelper,userRepository: UserRepository):SplashViewModel=ViewModelProvider(activity,ViewModelProviderFactory(SplashViewModel::class){
         SplashViewModel(schedulerProvider,compositeDisposable, networkHelper,userRepository)
    }).get(SplashViewModel::class.java)

   @Provides
   fun provideLoginViewModel(schedulerProvider: SchedulerProvider,compositeDisposable: CompositeDisposable,networkHelper: NetworkHelper,userRepository: UserRepository):LoginViewModel=ViewModelProvider(activity,ViewModelProviderFactory(LoginViewModel::class){
       LoginViewModel(schedulerProvider,compositeDisposable, networkHelper,userRepository)
   }).get(LoginViewModel::class.java)

   @Provides
   fun provideRegisterViewModel(schedulerProvider: SchedulerProvider,compositeDisposable: CompositeDisposable,networkHelper: NetworkHelper,userRepository: UserRepository):RegisterViewModel=ViewModelProvider(activity,ViewModelProviderFactory(RegisterViewModel::class){
       RegisterViewModel(schedulerProvider,compositeDisposable, networkHelper,userRepository)
   }).get(RegisterViewModel::class.java)


    @Provides
    fun provideMainViewModel(schedulerProvider: SchedulerProvider,compositeDisposable: CompositeDisposable,networkHelper: NetworkHelper):MainViewModel=ViewModelProvider(activity,ViewModelProviderFactory(MainViewModel::class){
        MainViewModel(schedulerProvider,compositeDisposable, networkHelper)
    }).get(MainViewModel::class.java)



    @Provides
    fun provideMainShareViewModel(schedulerProvider: SchedulerProvider,compositeDisposable: CompositeDisposable,networkHelper: NetworkHelper):MainShareViewModel=ViewModelProvider(activity,ViewModelProviderFactory(MainShareViewModel::class){
        MainShareViewModel(schedulerProvider,compositeDisposable, networkHelper)
    }).get(MainShareViewModel::class.java)

}