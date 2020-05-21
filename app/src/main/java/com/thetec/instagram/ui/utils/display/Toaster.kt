package com.thetec.instagram.ui.utils.display

import android.content.Context
import android.graphics.PorterDuff
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.thetec.instagram.R

object Toaster {

    fun showToast(context: Context, text:String)
    {
        val toast=Toast.makeText(context,text,Toast.LENGTH_LONG)
        toast.view.background.setColorFilter( ContextCompat.getColor(context, R.color.white), PorterDuff.Mode.SRC_IN)
        val textView = toast.view.findViewById(android.R.id.message) as TextView
        textView.setTextColor(ContextCompat.getColor(context, R.color.black))
        toast.show()
    }
}