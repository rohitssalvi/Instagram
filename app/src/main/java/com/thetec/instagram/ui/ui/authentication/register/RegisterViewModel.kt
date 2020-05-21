package com.thetec.instagram.ui.ui.authentication.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.thetec.instagram.ui.data.repository.UserRepository
import com.thetec.instagram.ui.ui.base.BaseViewModel
import com.thetec.instagram.ui.utils.commen.Resource
import com.thetec.instagram.ui.utils.commen.Status
import com.thetec.instagram.ui.utils.commen.ValidationCommen
import com.thetec.instagram.ui.utils.commen.Validator
import com.thetec.instagram.ui.utils.display.Toaster
import com.thetec.instagram.ui.utils.network.NetworkHelper
import com.thetec.instagram.ui.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class RegisterViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val userRepository: UserRepository
) : BaseViewModel(schedulerProvider,compositeDisposable, networkHelper) {


   private val validationList:MutableLiveData<List<ValidationCommen>> = MutableLiveData()
   val emailField: MutableLiveData<String> = MutableLiveData()
   val passwordField: MutableLiveData<String> = MutableLiveData()
   val nameField: MutableLiveData<String> = MutableLiveData()
   val isProgressVisible: MutableLiveData<Boolean> = MutableLiveData()

    val emailFieldValidation: LiveData<Resource<Int>> = filterValidation(ValidationCommen.Field.EMAIL)
    val passwordFieldValidation: LiveData<Resource<Int>> = filterValidation(ValidationCommen.Field.PASSWORD)
    val nameFieldValidation: LiveData<Resource<Int>> = filterValidation(ValidationCommen.Field.NAME)

    val signUpCompleteNavigateAction:MutableLiveData<Boolean> = MutableLiveData()

    val moveLoginScreen:MutableLiveData<Boolean> = MutableLiveData();


    private fun filterValidation(field:ValidationCommen.Field):LiveData<Resource<Int>> =
        Transformations.map(validationList) {
            it.find { validationCommen -> validationCommen.field == field }
                ?.run { return@run this.resource }
                ?: Resource.unknown()
        }




    override fun onCreate() {

    }

    fun onEmailChange(email : String )= emailField.postValue(email)

    fun onPasswordChange(password : String)= passwordField.postValue(password)

    fun onNameChange(name : String)=nameField.postValue(name)


    fun doSignUp()
    {
        val email=emailField.value
        val password=passwordField.value
        val name=nameField.value

        val validations=Validator.validateSignFields(name,email,password)

        validationList.postValue(validations)
        if( validations.isNotEmpty() && email!=null && password !=null && name !=null )
        {


             val successValidation=validations.filter { it.resource.status==Status.SUCCESS }
            if(successValidation.size==validations.size && checkInternetConnectionWithMessage())
            {
                isProgressVisible.postValue(true)
                compositeDisposable.add(
                    userRepository.doSignUp(email,password,name)
                        .subscribeOn(schedulerProvider.io())
                        .subscribe({
                            userRepository.saveCurrentUser(it)
                            isProgressVisible.postValue(false)
                            signUpCompleteNavigateAction.postValue(true)

                        },{
                            handleNetworkError(it)
                            isProgressVisible.postValue(false)
                            signUpCompleteNavigateAction.postValue(false)
                        })

                )


            }

        }



    }



    fun moveLoginScreen()
    {
       moveLoginScreen.postValue(true)
    }



}