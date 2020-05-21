package com.thetec.instagram.ui.data.repository

import android.util.Log
import com.thetec.instagram.ui.data.local.db.DatabaseService
import com.thetec.instagram.ui.data.local.prefrence.UserPreference
import com.thetec.instagram.ui.data.model.User
import com.thetec.instagram.ui.data.remote.NetworkService
import com.thetec.instagram.ui.data.remote.request.LoginRequest
import com.thetec.instagram.ui.data.remote.request.SigupRequest
import com.thetec.instagram.ui.utils.rx.RxschedulerProvider
import com.thetec.instagram.ui.utils.rx.SchedulerProvider
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val databaseService: DatabaseService,
    private val networkService: NetworkService,
    private val userPreference: UserPreference
)
{
    fun saveCurrentUser(user:User)
    {
        userPreference.setUserId(user.userId)
        userPreference.setUserEmail(user.userEmail)
        userPreference.setUserName(user.userName)
        userPreference.setAccessToken(user.accessToken)
        userPreference.setRefreshToken(user.refreshToken)

    }

    fun removeCurrentUser()
    {
        userPreference.removeUserId()
        userPreference.removeUserName()
        userPreference.removeUserEmail()
        userPreference.removeAccessToken()
        userPreference.removeRefereshToken()

    }

    fun getCurrentUser() : User?
    {
        val userId = userPreference.getUserId()
        val userName = userPreference.getUserName()
        val userEmail = userPreference.getUserEmail()
        val accessToken = userPreference.getAccessToken()
        val refereshToken=userPreference.getRefreshToken()
        return if (userId !== null && userName != null && userEmail != null && accessToken != null && refereshToken!=null)
            User(userId,userName,userEmail,accessToken,refereshToken)
        else
            null
    }

    fun doUserLogin(emailId:String,password:String):Single<User> = networkService
        .login(LoginRequest(emailId,password))
        .map {
            User(

                it.userId,
                it.userName,
                it.userEmail,
                it.accessToken,
                it.refreshToken

            )
        }




    fun doSignUp(emailId: String,password: String,name : String) : Single<User> =networkService.signUp(
         SigupRequest(emailId,password,name)).map {
         User(
              it.userId,
              it.userName,
              it.userEmail,
              it.accessToken,
              it.refreshToken

          )
    }


}