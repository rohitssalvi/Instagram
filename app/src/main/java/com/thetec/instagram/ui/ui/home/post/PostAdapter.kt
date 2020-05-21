package com.thetec.instagram.ui.ui.home.post

import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import com.thetec.instagram.ui.data.model.Post
import com.thetec.instagram.ui.ui.base.BaseAdapter


class PostAdapter(
    parentLifecycle: Lifecycle,
    data: ArrayList<Post>
) : BaseAdapter<Post,PostItemViewHolder>(parentLifecycle,data)

{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=PostItemViewHolder(parent)
}