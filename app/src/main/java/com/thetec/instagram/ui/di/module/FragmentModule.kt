package com.thetec.instagram.ui.di.module

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mindorks.paracamera.Camera
import com.thetec.instagram.ui.data.repository.PhotoRespository
import com.thetec.instagram.ui.data.repository.PostRepository
import com.thetec.instagram.ui.data.repository.UserRepository
import com.thetec.instagram.ui.di.ActivityContext
import com.thetec.instagram.ui.di.TempDirectory
import com.thetec.instagram.ui.ui.base.BaseFragment
import com.thetec.instagram.ui.ui.home.HomeViewModel
import com.thetec.instagram.ui.ui.home.post.PostAdapter
import com.thetec.instagram.ui.ui.main.MainShareViewModel
import com.thetec.instagram.ui.ui.photo.PhotoViewModel
import com.thetec.instagram.ui.ui.profile.ProfileViewModel
import com.thetec.instagram.ui.utils.ViewModelProviderFactory
import com.thetec.instagram.ui.utils.network.NetworkHelper
import com.thetec.instagram.ui.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.processors.PublishProcessor
import java.io.File

@Module
class FragmentModule(private val frgament : BaseFragment<*>) {

   @ActivityContext
   @Provides
   fun provideContext():Context?= frgament.context

   @Provides
   fun provideHomeViewModel(schedulerProvider: SchedulerProvider,
                            compositeDisposable: CompositeDisposable,
                            networkHelper: NetworkHelper,
                              userRepository: UserRepository,
                              postRepository: PostRepository):HomeViewModel=
      ViewModelProvider(frgament, ViewModelProviderFactory(HomeViewModel::class){
         HomeViewModel(schedulerProvider,compositeDisposable, networkHelper,userRepository,postRepository,
            ArrayList(),
            PublishProcessor.create
        ())
   }).get(HomeViewModel::class.java)


   @Provides
   fun providePhotoViewModel(schedulerProvider: SchedulerProvider,compositeDisposable: CompositeDisposable,networkHelper: NetworkHelper,userRepository: UserRepository,postRepository: PostRepository,photoRespository: PhotoRespository,@TempDirectory directory: File):PhotoViewModel=ViewModelProvider(frgament,ViewModelProviderFactory(PhotoViewModel::class){
      PhotoViewModel(schedulerProvider,compositeDisposable, networkHelper,userRepository, postRepository, photoRespository, directory)
   }).get(PhotoViewModel::class.java)


   @Provides
   fun provideProfileViewModel(schedulerProvider: SchedulerProvider,compositeDisposable: CompositeDisposable,networkHelper: NetworkHelper):ProfileViewModel=ViewModelProvider(frgament,ViewModelProviderFactory(ProfileViewModel::class){
      ProfileViewModel(schedulerProvider,compositeDisposable, networkHelper)
   }).get(ProfileViewModel::class.java)

   @Provides
   fun providePostAdapter() = PostAdapter(frgament.lifecycle, ArrayList())

   @Provides
   fun provideLinearLayoutMamager():LinearLayoutManager=LinearLayoutManager(frgament.context)


    @Provides
    fun provideMainShareViewModel(schedulerProvider: SchedulerProvider,compositeDisposable: CompositeDisposable,networkHelper: NetworkHelper):MainShareViewModel=ViewModelProvider(frgament.activity!!,ViewModelProviderFactory(MainShareViewModel::class){
        MainShareViewModel(schedulerProvider,compositeDisposable, networkHelper)
    }).get(MainShareViewModel::class.java)

   @Provides
   fun provideCameraInstance()= Camera.Builder()
                                 .resetToCorrectOrientation(true)
                                .setTakePhotoRequestCode(1)
                                .setDirectory("temp")
                                .setName("camera_temp_img")
                                .setImageFormat(Camera.IMAGE_JPEG)
                                .setCompression(75)
                                 .setImageHeight(1000)// it will try to achieve this height as close as possible maintaining the aspect ratio;
                                 .build(frgament);

}