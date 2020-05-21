package com.thetec.instagram.ui.data.remote

import com.thetec.instagram.ui.data.remote.request.CreatePostRequest
import com.thetec.instagram.ui.data.remote.request.LoginRequest
import com.thetec.instagram.ui.data.remote.request.PostLikeAndUnlikeRequest
import com.thetec.instagram.ui.data.remote.request.SigupRequest
import com.thetec.instagram.ui.data.remote.response.*
import com.thetec.instagram.ui.utils.commen.Constants
import io.reactivex.Single
import okhttp3.MultipartBody
import retrofit2.http.*

interface NetworkService {

    @POST(Endpoint.SIGNUP)
    fun signUp(
        @Body request:SigupRequest,
        @Header(Constants.HEADER_API_KEY) api_key:String=Networking.API_KEY
    ):Single<SignupResponse>


    @POST(Endpoint.LOGIN)
    fun login(
        @Body request:LoginRequest,
        @Header(Constants.HEADER_API_KEY) api_key:String=Networking.API_KEY
    ):Single<SignupResponse>


    @GET(Endpoint.ALLPOSTLIST)
    fun HomePostList(
        @Query("firstPostId")  firstPostId:String?,
        @Query("lastPostId")  lastPostId:String?,
        @Header(Constants.HEADER_USER_ID)  userId:String,
        @Header(Constants.HEADER_ACCESS_TOKEN)  accessToken:String,
        @Header(Constants.HEADER_API_KEY) apiKey:String = Networking.API_KEY
    ):Single<PostListResponse>


    @PUT(Endpoint.LIKEPOST)
    fun postLike(
        @Body postrequest:PostLikeAndUnlikeRequest,
        @Header(Constants.HEADER_USER_ID)  userId:String,
        @Header(Constants.HEADER_ACCESS_TOKEN)  accessToken:String,
        @Header(Constants.HEADER_API_KEY) apiKey:String = Networking.API_KEY
    ):Single<GeneralResponse>


    @PUT(Endpoint.UNLIKEPOST)
    fun postUnLike(
        @Body postrequest:PostLikeAndUnlikeRequest,
        @Header(Constants.HEADER_USER_ID)  userId:String,
        @Header(Constants.HEADER_ACCESS_TOKEN)  accessToken:String,
        @Header(Constants.HEADER_API_KEY) apiKey:String = Networking.API_KEY
    ):Single<GeneralResponse>


    @Multipart
    @POST(Endpoint.IMAGEUPLOAD)
    fun imageUpload(
        @Part image: MultipartBody.Part,
        @Header(Constants.HEADER_USER_ID) userId:String,
        @Header(Constants.HEADER_ACCESS_TOKEN) accessToken: String,
        @Header(Constants.HEADER_API_KEY) apiKey: String=Networking.API_KEY
    ) : Single<ImageResponse>

    @POST(Endpoint.CREATEINTAGARMPOST)
    fun createPost(
        @Body createPostRequest: CreatePostRequest,
        @Header(Constants.HEADER_USER_ID) userId:String,
        @Header(Constants.HEADER_ACCESS_TOKEN) accessToken: String,
        @Header(Constants.HEADER_API_KEY) apiKey: String=Networking.API_KEY
    ):Single<CreatePostResponse>


    @DELETE(Endpoint.LOGOUT)
    fun logoutUser(
        @Header(Constants.HEADER_USER_ID) userId:String,
        @Header(Constants.HEADER_ACCESS_TOKEN) accessToken: String,
        @Header(Constants.HEADER_API_KEY) apiKey: String=Networking.API_KEY
    ):Single<GeneralResponse>


    @GET(Endpoint.GETMYINFO)
    fun getProfileData(
        @Header(Constants.HEADER_USER_ID) userId:String,
        @Header(Constants.HEADER_ACCESS_TOKEN) accessToken: String,
        @Header(Constants.HEADER_API_KEY) apiKey: String=Networking.API_KEY
    ) : Single<ProfileResponse>


}