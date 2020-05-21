package com.thetec.instagram.ui.data.model

data class Image(
    val url : String,
    val headers: Map<String,String>,
    val placeHolderWidth : Int=-1,
    val placeHolderHeight : Int= -1
)