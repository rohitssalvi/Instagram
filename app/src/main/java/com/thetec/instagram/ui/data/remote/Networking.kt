package com.thetec.instagram.ui.data.remote

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.thetec.instagram.BuildConfig
import com.thetec.instagram.ui.utils.commen.Constants
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

object Networking {

    internal lateinit var API_KEY:String

    fun create(api_key:String,baseUrl:String,cacheDir:File,cacheSize:Long):NetworkService{
     API_KEY=api_key
        return Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .client(OkHttpClient
                .Builder()
                .cache(Cache(cacheDir,cacheSize))
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level= if(BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                    else HttpLoggingInterceptor.Level.NONE
                })
                .readTimeout(Constants.NETWORK_TIMEOUT.toLong(),TimeUnit.SECONDS)
                .writeTimeout(Constants.NETWORK_TIMEOUT.toLong(),TimeUnit.SECONDS)
                .build()

            )
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(NetworkService::class.java)

    }


}