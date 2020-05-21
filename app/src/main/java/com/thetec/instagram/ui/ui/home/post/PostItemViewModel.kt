package com.thetec.instagram.ui.ui.home.post

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.thetec.instagram.R
import com.thetec.instagram.ui.data.model.Image
import com.thetec.instagram.ui.data.model.Post
import com.thetec.instagram.ui.data.model.User
import com.thetec.instagram.ui.data.remote.NetworkService
import com.thetec.instagram.ui.data.remote.Networking
import com.thetec.instagram.ui.data.repository.PostRepository
import com.thetec.instagram.ui.data.repository.UserRepository
import com.thetec.instagram.ui.ui.base.BaseItemViewModel
import com.thetec.instagram.ui.utils.commen.Constants
import com.thetec.instagram.ui.utils.commen.Resource
import com.thetec.instagram.ui.utils.commen.TimesUtils
import com.thetec.instagram.ui.utils.display.ScreenUtils
import com.thetec.instagram.ui.utils.network.NetworkHelper
import com.thetec.instagram.ui.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class PostItemViewModel @Inject constructor(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    userRepository: UserRepository,
    private val postRepository: PostRepository

): BaseItemViewModel<Post>(schedulerProvider,compositeDisposable,networkHelper)
{

    companion object{
        const val TAG="PostItemViewModel"
    }


    private val user = userRepository.getCurrentUser()!!
    private val screenWidth=ScreenUtils.getScreenWidth()
    private val screenHeight=ScreenUtils.getScreenHeight()

    private val headers = mapOf(
    Pair(Constants.HEADER_API_KEY,Networking.API_KEY),
    Pair(Constants.HEADER_USER_ID,user.userId),
    Pair(Constants.HEADER_ACCESS_TOKEN,user.accessToken)
   )

   val name:LiveData<String> = Transformations.map(data){
       it.postCreator.postUserName
   }

   val postTime:LiveData<String> = Transformations.map(data){
     TimesUtils.getTimeAgo(it.postCreatedAt)
   }

   val likeCount:LiveData<Int> = Transformations.map(data){
       it.likedBy.size ?: 0
   }



    val isLiked:LiveData<Boolean> = Transformations.map(data){
        it.likedBy?.find { postUser-> postUser.postUserId==user.userId}!==null
    }

    /*val profileImage : LiveData<Image> = Transformations.map(data){
        Log.e(TAG,it.postCreator.postUserImageUrl)
        it.postCreator.postUserImageUrl?.run{
            Image(this,headers)
        }
    }*/

    val imagesDeatils : LiveData<Image> = Transformations.map(data){
        Image(
            it.imageUrl,
            headers,
            screenWidth,
            it.imgHeight?.let {
               height -> return@let (calculateScaleFactor(it)*height).toInt()
            } ?: screenHeight/3
        )
    }

    private fun calculateScaleFactor(post:Post)=post.imgWidth.let { return@let screenWidth.toFloat()/it  } ?: 1f

    fun onLikeClick()=data.value?.let {
        if(networkHelper.isNetworkConnected())
        {
              val api=
                  if(isLiked.value == true)
                      postRepository.makeUnlikePost(it,user)
                  else
                      postRepository.makeLikePost(it,user)

              compositeDisposable.addAll(
                  api
                 .subscribeOn(schedulerProvider.io())
                      .subscribe({
                         responsePost -> if(responsePost.postId==it.postId) updateData(responsePost)
                      },{
                       error-> handleNetworkError(error)
                      })

              )

        }else
        {
            stringMessageId.postValue(R.string.network_connection_error)
        }
    }

    override fun onCreate() {

    }



}