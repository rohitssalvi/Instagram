package com.thetec.instagram.ui.ui.main

import androidx.lifecycle.MutableLiveData
import com.thetec.instagram.ui.data.model.Post
import com.thetec.instagram.ui.ui.base.BaseViewModel
import com.thetec.instagram.ui.utils.commen.Event
import com.thetec.instagram.ui.utils.network.NetworkHelper
import com.thetec.instagram.ui.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class MainShareViewModel (
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    //communicate to activity
    val homeRedirection:MutableLiveData<Event<Boolean>> = MutableLiveData()

    //communicate to fragment
    val newPosts:MutableLiveData<Event<Post>> = MutableLiveData()

    override fun onCreate() {
        TODO("Not yet implemented")
    }


    fun onHomeRedirect()
    {
        homeRedirection.postValue(Event(true))
    }
}