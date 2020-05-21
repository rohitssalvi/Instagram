package com.thetec.instagram.ui.data.repository

import com.thetec.instagram.ui.data.local.db.DatabaseService
import com.thetec.instagram.ui.data.model.User
import com.thetec.instagram.ui.data.remote.NetworkService
import io.reactivex.Single
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import javax.inject.Inject

class PhotoRespository @Inject constructor(
  private val networkService: NetworkService

) {

    companion object{
        const val TAG="PhotoRespository"
    }


    fun uploadImage(imageFile: File,user:User):Single<String> {
        return MultipartBody.Part.createFormData(
            "image",imageFile.name, RequestBody.create(MediaType.parse("image/*"),imageFile)).run {

            return@run networkService.imageUpload(this,user.userId,user.accessToken).map {
                it.data.imageUrl
            }
        }
    }
}