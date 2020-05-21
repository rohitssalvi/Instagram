package com.thetec.instagram.ui.ui.splash

import androidx.lifecycle.MutableLiveData
import com.thetec.instagram.ui.data.repository.UserRepository
import com.thetec.instagram.ui.ui.base.BaseViewModel
import com.thetec.instagram.ui.utils.network.NetworkHelper
import com.thetec.instagram.ui.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class SplashViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val userRepository: UserRepository
) : BaseViewModel(schedulerProvider,compositeDisposable,networkHelper) {

    val lauchActionLoggedIn:MutableLiveData<Boolean> =  MutableLiveData()

    override fun onCreate() {

        if(userRepository.getCurrentUser()!=null)
        {
            lauchActionLoggedIn.postValue(true)

        }else
        {
            lauchActionLoggedIn.postValue(false)
        }

    }


}