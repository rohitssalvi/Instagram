package com.thetec.instagram.ui.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thetec.instagram.R
import com.thetec.instagram.ui.utils.display.Toaster
import com.thetec.instagram.ui.utils.network.NetworkHelper
import com.thetec.instagram.ui.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel(
    protected val schedulerProvider: SchedulerProvider,
    protected val compositeDisposable: CompositeDisposable,
    protected val networkHelper: NetworkHelper
) : ViewModel()
{

    val stringMessageId=MutableLiveData<Int>()
    val stringMessage=MutableLiveData<String>()


    protected fun checkInternetConnection():Boolean=networkHelper.isNetworkConnected()

    protected fun checkInternetConnectionWithMessage():Boolean
    {
        if(networkHelper.isNetworkConnected())
          return  true
        else
         stringMessageId.postValue(R.string.network_connection_error)
         return false

    }


     protected fun handleNetworkError(err:Throwable)
     {
            stringMessage.postValue(err.message)
     }

     override fun onCleared() {
            compositeDisposable.dispose()
            super.onCleared()
        }

        abstract fun onCreate()
}

