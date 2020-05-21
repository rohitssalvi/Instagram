package com.thetec.instagram.ui.ui.photo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.mindorks.paracamera.Camera
import com.thetec.instagram.R
import com.thetec.instagram.ui.di.component.FragmentComponent
import com.thetec.instagram.ui.ui.base.BaseFragment
import com.thetec.instagram.ui.ui.main.MainShareViewModel
import com.thetec.instagram.ui.utils.commen.Event
import kotlinx.android.synthetic.main.fragment_photo.*
import java.io.FileNotFoundException
import javax.inject.Inject

class PhotoFragment : BaseFragment<PhotoViewModel>() {

    companion object{
        const val TAG="PhotoFragment"
        const val GALLERY_IMAGE_REQ_ID=121

        fun newInstance():PhotoFragment{
            val arg=Bundle()
            val photoFragment=PhotoFragment()
            photoFragment.arguments=arg
            return photoFragment
        }
    }
   @Inject
    lateinit var camera : Camera
    @Inject
    lateinit var mainShareViewModel: MainShareViewModel

    override fun provideLaoutID(): Int = R.layout.fragment_photo

    override fun setupView(viw: View?, savebundle: Bundle?) {

        rlGallery.setOnClickListener {
            Intent(Intent.ACTION_PICK)
                .apply {
                    type="image/*"
                }.run {
                  startActivityForResult(this,GALLERY_IMAGE_REQ_ID)
                }
        }


        rlCamera.setOnClickListener {
          camera.takePicture()
        }
    }

    override fun injectDependency(fragmentComponent: FragmentComponent) {

        fragmentComponent.inject(this)
    }

    override fun setupObserver() {
        super.setupObserver()

        viewModel.loading.observe(this, Observer {
            progressPhoto.visibility=  if(it) View.VISIBLE else View.GONE
        })

        viewModel.posts.observe(this, Observer {
            it.getIfNotHandled()?.run {
                mainShareViewModel.newPosts.postValue(Event(this))
                mainShareViewModel.onHomeRedirect()
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode==Activity.RESULT_OK)
        {
            when(requestCode) {
                GALLERY_IMAGE_REQ_ID -> {
                    try {
                        data?.data?.let {
                            activity?.contentResolver?.openInputStream(it)?.run {
                                viewModel.onGalleryImageSelected(this)
                            } ?: showMessage("try again")
                        }

                    } catch (e: FileNotFoundException) {
                        e.printStackTrace()
                        showMessage("try again")
                    }
                }


                Camera.REQUEST_TAKE_PHOTO -> {
                    viewModel.onCameraImageTaken { camera.cameraBitmapPath }
                }
            }
        }

    }
}
