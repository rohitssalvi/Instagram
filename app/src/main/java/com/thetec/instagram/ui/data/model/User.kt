package com.thetec.instagram.ui.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import org.w3c.dom.Text

data class User(
    @Expose
    @SerializedName("userId")
    var userId:String,

    @Expose
    @SerializedName("userName")
    var userName:String,

    @Expose
    @SerializedName("userEmail")
    var userEmail:String,

    @Expose
    @SerializedName("accessToken")
    var accessToken:String,

    @Expose
    @SerializedName("refreshToken")
    var refreshToken:String


)