package com.thetec.instagram.ui.data.repository

import android.util.Log
import com.thetec.instagram.ui.data.local.db.DatabaseService
import com.thetec.instagram.ui.data.model.Post
import com.thetec.instagram.ui.data.model.User
import com.thetec.instagram.ui.data.remote.NetworkService
import com.thetec.instagram.ui.data.remote.request.CreatePostRequest
import com.thetec.instagram.ui.data.remote.request.PostLikeAndUnlikeRequest
import io.reactivex.Single
import retrofit2.http.Body
import javax.inject.Inject

class PostRepository @Inject constructor(
   private val networkService: NetworkService,
    private val databaseService: DatabaseService
) {
   companion object{
       const val TAG="PostRepository"
   }
    fun fetchHomePostList(firstPostId:String?, lastPostId:String?,user:User):Single<List<Post>>
    {
        return networkService.HomePostList(
            firstPostId,
            lastPostId,
            user.userId,
            user.accessToken
        ).map {
            it.data
        }
    }


    fun makeLikePost(post:Post,user:User):Single<Post> {
       return networkService.postLike(PostLikeAndUnlikeRequest(post.postId), user.userId,user.accessToken)
            .map {
                post.likedBy.apply {
                    this.find { postUser -> postUser.postUserId == user.userId } ?: this.add(
                      Post.User(
                          user.userId,
                          user.userName,
                          user.userEmail
                      )
                    )
                }
                return@map post
            }
    }



    fun makeUnlikePost(post:Post,user:User):Single<Post> {
        return networkService.postUnLike(PostLikeAndUnlikeRequest(post.postId), user.userId,user.accessToken)
            .map {
                post.likedBy.apply {
                    this.find { postUser -> postUser.postUserId == user.userId } . let {
                        remove(it)
                    }
                }
                return@map post
            }
    }


    fun createPost(url:String,imageWidth:Int,imageHeight:Int,user:User):Single<Post>
    {
      return  networkService.createPost(CreatePostRequest(url,imageWidth,imageHeight),user.userId,user.accessToken).map {
             Post(
                 it.data.id,
                 it.data.imgUrl,
                 it.data.imgWidth,
                 it.data.imgHeight,
                 Post.User(
                     user.userId,
                     user.userName,
                     ""

                 ),
                 mutableListOf(),
                 it.data.createdAt
                )
             }
   }


}