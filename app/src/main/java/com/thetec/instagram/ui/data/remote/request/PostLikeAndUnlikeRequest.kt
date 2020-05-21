package com.thetec.instagram.ui.data.remote.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PostLikeAndUnlikeRequest(
    @Expose
    @SerializedName("postId")
    var postId:String
)