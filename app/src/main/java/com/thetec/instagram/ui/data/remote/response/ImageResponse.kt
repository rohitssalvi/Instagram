package com.thetec.instagram.ui.data.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ImageResponse(

    @Expose
    @SerializedName("statusCode")
    val statusCode:String,

    @Expose
    @SerializedName("status")
    val status:Int,

    @Expose
    @SerializedName("message")
    val message:String,

    @Expose
    @SerializedName("data")
    val data:imageData


)
{
    data class imageData(
        @Expose
        @SerializedName("imageUrl")
        val imageUrl:String
    )
}