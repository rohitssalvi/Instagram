package com.thetec.instagram.ui.ui.base

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.thetec.instagram.ui.di.component.ActivityComponent
import com.thetec.instagram.ui.utils.network.NetworkHelper
import com.thetec.instagram.ui.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

abstract class BaseItemViewModel<T: Any>(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper
) : BaseViewModel(schedulerProvider,compositeDisposable,networkHelper)
{
   val data=MutableLiveData<T>();

    fun onManualCleard()=onCleared()

     fun updateData(data : T)
    {
        this.data.postValue(data)
    }


}