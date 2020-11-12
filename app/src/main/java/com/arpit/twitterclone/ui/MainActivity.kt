package com.arpit.twitterclone.ui

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.observe
import com.arpit.twitterclone.R
import com.arpit.twitterclone.databinding.ActivityMainBinding
import com.arpit.twitterclone.model.Post
import com.arpit.twitterclone.model.State
import com.arpit.twitterclone.ui.adapter.PostListAdapter
import com.arpit.twitterclone.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: BaseActivity<MainViewModel, ActivityMainBinding>() {

    override val mViewModel: MainViewModel by viewModels()
    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
    private val mAdapter = PostListAdapter(this::onItemClicked)


    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme_TwitterClone) // Set AppTheme before setting content view.

        super.onCreate(savedInstanceState)
        setContentView(mViewBinding.root)

        mViewBinding.postRecyclerView.adapter = mAdapter

        initPosts()
        fetchData()
        setUpEditText()
    }

    private fun setUpEditText() {
        // Get input text
        mViewBinding.outlinedTextField.editText?.doOnTextChanged { inputText, _, _, _ ->
            // Respond to input text change
            searchByValue(inputText.toString())
        }

        mViewModel.postsSearchLiveData.observe(this) {
            mAdapter.submitList(it.toMutableList())
            showLoading(false)
        }
    }

    private fun initPosts() {
        mViewModel.postsLiveData.observe(this) { state ->
            when (state) {
                is State.Loading -> showLoading(true)
                is State.Success -> {
                    if (state.data.isNotEmpty()) {
                        mAdapter.submitList(state.data.toMutableList())
                        showLoading(false)
                    }
                }
                is State.Error -> {
                    showToast(state.message)
                    showLoading(false)
                }
            }
        }

        mViewBinding.swipeRefreshLayout.setOnRefreshListener {
            getPosts()
        }

        // If State isn't `Success` then reload posts.
        if (mViewModel.postsLiveData.value !is State.Success) {
            getPosts()
        }
    }

    private fun searchByValue(string:String){
        showLoading(true)
        mViewModel.getPostsBySearchVal(string)
    }



    private fun showLoading(isLoading: Boolean) {
        mViewBinding.swipeRefreshLayout.isRefreshing = isLoading
    }


    private fun fetchData() {

        if (mViewModel.postsLiveData.value is State.Error || mAdapter.itemCount == 0) {
            getPosts()
        }

    }

    private fun getPosts() {
        mViewModel.getPosts()
    }

    private fun onItemClicked(post: Post, imageView: ImageView) {
        showToast("Item clicked")
    }


}