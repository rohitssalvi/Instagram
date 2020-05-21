package com.thetec.instagram.ui.data.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

data class CreatePostResponse(
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
    val data:createPost
){

    data class createPost(
        @Expose
        @SerializedName("id")
        val id:String,

        @Expose
        @SerializedName("imgUrl")
        val imgUrl:String,

        @Expose
        @SerializedName("imgWidth")
        val imgWidth:Int,

        @Expose
        @SerializedName("imgHeight")
        val imgHeight:Int,

        @Expose
        @SerializedName("createdAt")
        val createdAt:Date
    )
}