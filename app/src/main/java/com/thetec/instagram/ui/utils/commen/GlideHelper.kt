package com.thetec.instagram.ui.utils.commen

import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders

object GlideHelper {

    fun getProtectedUrl(url:String,heades : Map<String,String>):GlideUrl{
        val builder = LazyHeaders.Builder()
        for(entry in heades) builder.addHeader(entry.key,entry.value)
        return GlideUrl(url,builder.build())

    }
}