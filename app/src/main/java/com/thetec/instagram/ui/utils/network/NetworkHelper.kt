package com.thetec.instagram.ui.utils.network

import android.content.Context
import android.net.ConnectivityManager
import javax.inject.Inject

class NetworkHelper (private val context:Context) {

    companion object{
        const val TAG="NetworkHelper"
    }

    fun isNetworkConnected():Boolean
    {
        val cm=context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeInternert=cm.activeNetworkInfo
        return activeInternert?.isConnected?:false
    }
}