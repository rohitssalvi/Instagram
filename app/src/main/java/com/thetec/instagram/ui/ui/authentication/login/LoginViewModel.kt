package com.thetec.instagram.ui.ui.authentication.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.thetec.instagram.ui.data.repository.UserRepository
import com.thetec.instagram.ui.ui.base.BaseViewModel
import com.thetec.instagram.ui.utils.commen.*
import com.thetec.instagram.ui.utils.network.NetworkHelper
import com.thetec.instagram.ui.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import kotlin.math.log

class LoginViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val userRepository: UserRepository
) :BaseViewModel(schedulerProvider,compositeDisposable, networkHelper)
{
    private val validationsList: MutableLiveData<List<ValidationCommen>> = MutableLiveData()
    // check when user change email and password
    val emailField:MutableLiveData<String> = MutableLiveData()
    val passwordField:MutableLiveData<String> = MutableLiveData()

    // check progress bar visibllity
    val isLogin:MutableLiveData<Boolean> = MutableLiveData()
    val isBusttonVisible:MutableLiveData<Boolean> = MutableLiveData()

    // check user enter email id and password is valid or not empty
    val emailValidation:LiveData<Resource<Int>> = filterValidation(ValidationCommen.Field.EMAIL)
    val passwordValidation:LiveData<Resource<Int>> = filterValidation(ValidationCommen.Field.PASSWORD)

    val lauchAction:MutableLiveData<Boolean> = MutableLiveData()

    val moveSignUpScreen:MutableLiveData<Boolean> = MutableLiveData();



    override fun onCreate() {

    }

    private fun filterValidation(field: ValidationCommen.Field) =
        Transformations.map(validationsList) {
            it.find { validation -> validation.field == field }
                ?.run { return@run this.resource }
                ?: Resource.unknown()
        }
    // this function call from activity when user update/change Email ID in edit field
    fun onEmailChange(email:String)=emailField.postValue(email)
    // this function call from activity when user update/change Password in edit field
    fun onPasswordChange(password:String)= passwordField.postValue(password)


    fun doLogin()
    {

        val email=emailField.value
        val password =passwordField.value

        val validations = Validator.validateLoginFields(email, password)
        validationsList.postValue(validations)


        if(validations.isNotEmpty() && email!=null && password!=null)
        {

           /* val successValidationEmail=emailValidation.value?.status
            val successValidationPassword=passwordValidation.value?.status*/
            val successValidation = validations.filter { it.resource.status == Status.SUCCESS }

            if(successValidation.size == validations.size && checkInternetConnectionWithMessage())
            {
                isLogin.postValue(true)
                isBusttonVisible.postValue(true)
                compositeDisposable.addAll(
                    userRepository.doUserLogin(email,password)
                        .subscribeOn(schedulerProvider.io())
                         .subscribe(
                        {
                            isLogin.postValue(false)
                            isBusttonVisible.postValue(false)
                            userRepository.saveCurrentUser(it)
                            lauchAction.postValue(true)
                       },
                        {
                         handleNetworkError(it)
                         isLogin.postValue(false)
                         isBusttonVisible.postValue(false)
                         lauchAction.postValue(false)
                        }
                      )


                )

            }
        }
    }

    fun moveSignUpScreen()
    {
        moveSignUpScreen.postValue(true)
    }



}