package com.thetec.instagram.ui.ui.main

import androidx.lifecycle.MutableLiveData
import com.thetec.instagram.ui.ui.base.BaseViewModel
import com.thetec.instagram.ui.ui.home.HomeFragment
import com.thetec.instagram.ui.utils.network.NetworkHelper
import com.thetec.instagram.ui.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class MainViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    val homeFragmentShow=MutableLiveData<Boolean>()
    val photoFragmentShow=MutableLiveData<Boolean>()
    val profileFragmentShow=MutableLiveData<Boolean>()


    override fun onCreate() {
      homeFragmentShow.postValue(true)
    }

    fun homeSelected()
    {
        homeFragmentShow.postValue(true)
    }


    fun photoSelected()
    {
        photoFragmentShow.postValue(true)
    }

    fun profileSelected()
    {
        profileFragmentShow.postValue(true)

    }

}