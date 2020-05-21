package com.thetec.instagram.ui.data.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    @Expose
    @SerializedName("statusCode")
    var statusCode:String,

    @Expose
    @SerializedName("status")
    var status:Int,

    @Expose
    @SerializedName("data")
    val data:List<profileData>


){

    data class profileData(
        @Expose
        @SerializedName("id")
        var id:String,

        @Expose
        @SerializedName("name")
        var name:String,

        @Expose
        @SerializedName("profilePicUrl")
        var profilePicUrl:String,

        @Expose
        @SerializedName("tagline")
        var tagline:String

    )
}