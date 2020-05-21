package com.thetec.instagram.ui.ui.photo

import androidx.lifecycle.MutableLiveData
import com.thetec.instagram.R
import com.thetec.instagram.ui.data.model.Post
import com.thetec.instagram.ui.data.model.User
import com.thetec.instagram.ui.data.repository.PhotoRespository
import com.thetec.instagram.ui.data.repository.PostRepository
import com.thetec.instagram.ui.data.repository.UserRepository
import com.thetec.instagram.ui.ui.base.BaseViewModel
import com.thetec.instagram.ui.utils.commen.Event
import com.thetec.instagram.ui.utils.commen.FileUtils
import com.thetec.instagram.ui.utils.commen.Resource
import com.thetec.instagram.ui.utils.network.NetworkHelper
import com.thetec.instagram.ui.utils.rx.SchedulerProvider
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import java.io.File
import java.io.InputStream

class PhotoViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val userRepository: UserRepository,
    private val postRepository: PostRepository,
    private val photoRespository: PhotoRespository,
    private val directory: File
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    private val user: User = userRepository.getCurrentUser()!!
    val loading : MutableLiveData<Boolean> = MutableLiveData()
    val posts:MutableLiveData<Event<Post>> = MutableLiveData()


    fun onGalleryImageSelected(inputStream : InputStream)
    {
        loading.postValue(true)
        compositeDisposable.add(
            Single.fromCallable{
                FileUtils.saveInputStreamToFile(inputStream,directory,"gallery_img_temp",500)
            }
           .subscribeOn(schedulerProvider.io())
                .subscribe(
                    {
                         if(it!=null)
                         {
                             FileUtils.getImageSize(it)?.run {
                                 uploadPhotoAndCreatePost(it, this)
                             }

                         }else
                         {
                             loading.postValue(false)
                             stringMessage.postValue("Try Again")
                         }
                    },
                    {
                        loading.postValue(false)

                        stringMessage.postValue("Try Again")
                    }
                )
        )
    }


    fun onCameraImageTaken(cameraImageProcessor: () -> String) {
        loading.postValue(true)
        compositeDisposable.add(
            Single.fromCallable { cameraImageProcessor() }
                .subscribeOn(schedulerProvider.io())
                .subscribe(
                    {
                        File(it).apply {
                            FileUtils.getImageSize(this)?.let { size ->
                                uploadPhotoAndCreatePost(this, size)
                            } ?: loading.postValue(false)
                        }
                    },
                    {
                        loading.postValue(false)
                        stringMessage.postValue("Try Again")
                    }
                )
        )
    }


    private fun uploadPhotoAndCreatePost(imageFile : File,size:Pair<Int,Int>)
    {

        compositeDisposable.add(
            photoRespository.uploadImage(imageFile,user).flatMap {
                postRepository.createPost(it,size.first,size.second,user)
            }.subscribeOn(schedulerProvider.io())
                .subscribe({
                  loading.postValue(false)
                  posts.postValue(Event(it))
                },
                {
                    handleNetworkError(it)
                    loading.postValue(false)
                })

        )



    }

    override fun onCreate() {

    }

}