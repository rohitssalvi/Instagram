package com.thetec.instagram.ui.data.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.thetec.instagram.ui.data.model.Post

data class PostListResponse(
    @Expose
    @SerializedName("statusCode")
    var statusCode:String,

    @Expose
    @SerializedName("status")
    var status:Int,

    @Expose
    @SerializedName("data")
    val data:List<Post>

)