package com.thetec.instagram.ui.data.remote.request

import android.R
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SigupRequest(
    @Expose
    @SerializedName("email")
    var email:String,

    @Expose
    @SerializedName("password")
    var password:String,

    @Expose
    @SerializedName("name")
    var name:String

)