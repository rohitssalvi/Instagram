package com.thetec.instagram.ui.utils.display

import android.content.res.Resources

object ScreenUtils {

    fun getScreenWidth():Int=Resources.getSystem().displayMetrics.widthPixels

    fun getScreenHeight():Int=Resources.getSystem().displayMetrics.heightPixels
}