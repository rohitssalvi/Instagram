package com.thetec.instagram.ui.ui.home.post

import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.thetec.instagram.R
import com.thetec.instagram.ui.data.model.Post
import com.thetec.instagram.ui.data.model.User
import com.thetec.instagram.ui.di.component.ViewHolderComponent
import com.thetec.instagram.ui.ui.base.BaseItemViewHolder
import com.thetec.instagram.ui.utils.commen.GlideHelper
import kotlinx.android.synthetic.main.item_view_post.view.*

class PostItemViewHolder(
    parent : ViewGroup
): BaseItemViewHolder<Post,PostItemViewModel>(R.layout.item_view_post,parent) {

    companion object{
        const val TAG="PostItemViewHolder"
    }




    override fun setupView(view: View?) {
        itemView.likeUnlikeImage.setOnClickListener{
            viewModel.onLikeClick()
        }
    }



    override fun injectDependency(viewHolderComponent: ViewHolderComponent) {

        viewHolderComponent.inject(this)
    }

    override fun setupObserver() {
        super.setupObserver()
        viewModel.name.observe(this, Observer {
            itemView.userName.text=it
        })


        viewModel.postTime.observe(this, Observer {
            itemView.txtTime.text=it
        })


        viewModel.likeCount.observe(this, Observer {
            itemView.postCount.text=itemView.context.getString(R.string.post_like,it)
        })

        viewModel.isLiked.observe(this, Observer {
            if(it) itemView.likeUnlikeImage.setImageResource(R.drawable.ic_heart_selected)
                else
                itemView.likeUnlikeImage.setImageResource(R.drawable.ic_heart_unselected)
        })

        /*viewModel.profileImage.observe(this, Observer {
            it.run {
                val glideRequest=Glide.with(itemView.userProfileImage.context)
                    .load(GlideHelper.getProtectedUrl(url,headers))
                    .apply(RequestOptions.circleCropTransform())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_profile_selected))

                   if(placeHolderWidth>0 && placeHolderHeight>0)
                   {
                       val params=itemView.userProfileImage.layoutParams as ViewGroup.LayoutParams
                       params.width=placeHolderWidth
                       params.height=placeHolderHeight
                       itemView.userProfileImage.layoutParams=params
                       glideRequest.apply(RequestOptions.overrideOf(placeHolderWidth,placeHolderHeight))
                           .apply(RequestOptions.placeholderOf(R.drawable.ic_profile_unselected))



                   }

                glideRequest.into(itemView.userProfileImage)

            }
        })*/


        viewModel.imagesDeatils.observe(this, Observer {
            it.run {
                val glideRequest=Glide.with(itemView.postImage.context)
                    .load(GlideHelper.getProtectedUrl(url,headers))


                if(placeHolderWidth>0 && placeHolderHeight>0)
                {
                    val params=itemView.postImage.layoutParams as ViewGroup.LayoutParams
                    params.width=placeHolderWidth
                    params.height=placeHolderHeight
                    itemView.postImage.layoutParams=params
                    glideRequest.apply(RequestOptions.overrideOf(placeHolderWidth,placeHolderHeight))
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_add_photos_selected))



                }

                glideRequest.into(itemView.postImage)

            }
        })





    }


}