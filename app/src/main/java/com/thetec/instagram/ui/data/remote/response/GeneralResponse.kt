package com.thetec.instagram.ui.data.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GeneralResponse(
    @Expose
    @SerializedName("statusCode")
    var statusCode:String,

    @Expose
    @SerializedName("status")
    var status:Int,

    @Expose
    @SerializedName("message")
    var message:String


)