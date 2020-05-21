package com.thetec.instagram.ui.data.local.prefrence

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPreference @Inject constructor(private val sharedPreferences: SharedPreferences)
{

    companion object {
        const val KEY_USER_ID = "PREF_KEY_USER_ID"
        const val KEY_USER_NAME = "PREF_KEY_USER_NAME"
        const val KEY_USER_EMAIL = "PREF_KEY_USER_EMAIL"
        const val KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN"
        const val KEY_REFRESH_TOKEN = "PREF_KEY_REFERESH_TOKEN"

    }

    fun getUserId(): String? =
        sharedPreferences.getString(KEY_USER_ID, null)

    fun setUserId(userId: String) =
        sharedPreferences.edit().putString(KEY_USER_ID, userId).apply()

    fun setRefreshToken(refreshToken: String) =
        sharedPreferences.edit().putString(KEY_REFRESH_TOKEN, refreshToken).apply()

    fun removeUserId() =
        sharedPreferences.edit().remove(KEY_USER_ID).apply()

    fun getUserName(): String? =
        sharedPreferences.getString(KEY_USER_NAME, null)

    fun getRefreshToken(): String? =
        sharedPreferences.getString(KEY_REFRESH_TOKEN, null)

    fun setUserName(userName: String) =
        sharedPreferences.edit().putString(KEY_USER_NAME, userName).apply()

    fun removeUserName() =
        sharedPreferences.edit().remove(KEY_USER_NAME).apply()

    fun getUserEmail(): String? =
        sharedPreferences.getString(KEY_USER_EMAIL, null)


    fun setUserEmail(email: String) =
        sharedPreferences.edit().putString(KEY_USER_EMAIL, email).apply()

    fun removeUserEmail() =
        sharedPreferences.edit().remove(KEY_USER_EMAIL).apply()

    fun removeRefereshToken() =
        sharedPreferences.edit().remove(KEY_REFRESH_TOKEN).apply()

    fun getAccessToken(): String? =
        sharedPreferences.getString(KEY_ACCESS_TOKEN, null)

    fun setAccessToken(token: String) =
        sharedPreferences.edit().putString(KEY_ACCESS_TOKEN, token).apply()

    fun removeAccessToken() =
        sharedPreferences.edit().remove(KEY_ACCESS_TOKEN).apply()

}