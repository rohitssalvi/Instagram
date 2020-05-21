package com.thetec.instagram.ui.ui.editprofile

import com.thetec.instagram.ui.ui.base.BaseViewModel
import com.thetec.instagram.ui.utils.network.NetworkHelper
import com.thetec.instagram.ui.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class EditProfileViewModel(
    schedulerProvider: SchedulerProvider,
compositeDisposable: CompositeDisposable,
networkHelper: NetworkHelper) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {
    override fun onCreate() {
    }
}