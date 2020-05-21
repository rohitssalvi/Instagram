package com.thetec.instagram.ui.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.thetec.instagram.ui.data.model.Post
import com.thetec.instagram.ui.data.model.User
import com.thetec.instagram.ui.data.repository.PostRepository
import com.thetec.instagram.ui.data.repository.UserRepository
import com.thetec.instagram.ui.ui.base.BaseViewModel
import com.thetec.instagram.ui.utils.commen.Resource
import com.thetec.instagram.ui.utils.network.NetworkHelper
import com.thetec.instagram.ui.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.processors.PublishProcessor

class HomeViewModel (
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val userRepository: UserRepository,
    private val postRepository: PostRepository,
    private val allPostList: ArrayList<Post>,
    private val paginator : PublishProcessor<Pair<String?,String?>>
):BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    companion object{
        const val TAG="HomeViewModel"
    }
    val loading : MutableLiveData<Boolean> = MutableLiveData()
    val posts : MutableLiveData<Resource<List<Post>>> = MutableLiveData()
    val refreshPost:MutableLiveData<Resource<List<Post>>> = MutableLiveData()
    var firstPostId:String?=null
    var lastPostId:String?=null
   private val user:User= userRepository.getCurrentUser()!!


    init {
        compositeDisposable.addAll(
            paginator
                .onBackpressureDrop()
                .doOnNext {
                   loading.postValue(true)
                }
                .concatMapSingle {
                    return@concatMapSingle postRepository
                        .fetchHomePostList(it.first,it.second,user)
                        .subscribeOn(schedulerProvider.io())
                        .doOnError {
                            handleNetworkError(it)
                        }
                }.subscribe ({

                    allPostList.addAll(it)
                    firstPostId=allPostList.maxBy { post -> post.postCreatedAt.time  }?.postId
                    lastPostId=allPostList.minBy { post -> post.postCreatedAt.time  }?.postId
                    loading.postValue(false)
                    posts.postValue(Resource.success(it))
                },
                 {
                     handleNetworkError(it)
                })
        )

    }

    override fun onCreate() {
       loadMorePosts()
    }

    private fun loadMorePosts() {
        if(checkInternetConnectionWithMessage()) paginator.onNext(Pair(firstPostId,lastPostId))

    }


    fun onLoadMore()
    {
        if(loading.value!==null && loading.value==false) loadMorePosts()
    }

    fun onNewPost(post:Post){
        allPostList.add(0,post)
        refreshPost.postValue(Resource.success(mutableListOf<Post>().apply { addAll(allPostList) }))
    }
}