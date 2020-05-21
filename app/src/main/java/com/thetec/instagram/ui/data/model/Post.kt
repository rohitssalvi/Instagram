package com.thetec.instagram.ui.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

data class Post(
    @Expose
    @SerializedName("id")
    var postId:String,

    @Expose
    @SerializedName("imgUrl")
    var imageUrl:String,

    @Expose
    @SerializedName("imgWidth")
    var imgWidth:Int,

    @Expose
    @SerializedName("imgHeight")
    var imgHeight:Int,

    @Expose
    @SerializedName("user")
    var postCreator:User,

    @Expose
    @SerializedName("likedBy")
    var likedBy:MutableList<User>,

    @Expose
    @SerializedName("createdAt")
    var postCreatedAt:Date

    ){

    data class User(

        @Expose
        @SerializedName("id")
        var postUserId:String,
        @Expose
        @SerializedName("name")
        var postUserName:String,
        @Expose
        @SerializedName("profilePicUrl")
        var postUserImageUrl:String
    )

}