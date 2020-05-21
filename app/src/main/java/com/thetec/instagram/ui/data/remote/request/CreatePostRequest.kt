package com.thetec.instagram.ui.data.remote.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CreatePostRequest(
    @Expose
    @SerializedName("imgUrl")
    val imagUrl:String,

    @Expose
    @SerializedName("imgWidth")
    val imgWidth:Int,

    @Expose
    @SerializedName("imgHeight")
    val imgHeight:Int
)