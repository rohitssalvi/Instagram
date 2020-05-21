package com.thetec.instagram.ui.ui.home

import android.os.Bundle
import android.view.View
import android.widget.AbsListView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thetec.instagram.R
import com.thetec.instagram.ui.di.component.FragmentComponent
import com.thetec.instagram.ui.ui.base.BaseActivity
import com.thetec.instagram.ui.ui.base.BaseFragment
import com.thetec.instagram.ui.ui.home.post.PostAdapter
import com.thetec.instagram.ui.ui.main.MainShareViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment : BaseFragment<HomeViewModel>() {

    companion object{
        const val TAG="HomeFragment"

        fun newInstance():HomeFragment{
            val arg=Bundle()
            val homeFragment=HomeFragment()
            homeFragment.arguments=arg
            return homeFragment

        }
    }

    @Inject
     lateinit var linearLayoutManager: LinearLayoutManager
    @Inject
    lateinit var mainShareViewModel: MainShareViewModel
    @Inject
     lateinit var postAdapter: PostAdapter



    override fun provideLaoutID(): Int = R.layout.fragment_home

    override fun setupView(viw: View?, savebundle: Bundle?) {
       recyclerView.apply {
              layoutManager=linearLayoutManager
              adapter=postAdapter
             addOnScrollListener(object : RecyclerView.OnScrollListener(){
                 override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                     super.onScrolled(recyclerView, dx, dy)
                     layoutManager?.run {
                         if(this is LinearLayoutManager && itemCount>0 && itemCount==findLastVisibleItemPosition()+1)
                             viewModel.onLoadMore()
                     }
                 }


             })
       }
    }

    override fun injectDependency(fragmentComponent: FragmentComponent) {
      fragmentComponent.inject(this)
    }

    override fun setupObserver() {
        super.setupObserver()
        viewModel.loading.observe(this, Observer {
            progressHome.visibility= if(it) View.VISIBLE else View.GONE
        })

        viewModel.posts.observe(this, Observer {
           it.data?.run {
               postAdapter.appendData(this)
           }
        })

        mainShareViewModel.newPosts.observe(this, Observer {
            it.getIfNotHandled()?.run {
                viewModel.onNewPost(this) }
        })

        viewModel.refreshPost.observe(this, Observer {
            it.data?.run {
                postAdapter.updateList(this)
                recyclerView.scrollToPosition(0)
            }
        })

    }


}