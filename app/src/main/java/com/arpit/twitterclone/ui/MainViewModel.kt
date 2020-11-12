package com.arpit.twitterclone.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arpit.twitterclone.data.repository.PostsRepository
import com.arpit.twitterclone.model.Post
import com.arpit.twitterclone.model.State
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Main view model
 * postsRepository - Repository as input
 */
class MainViewModel @ViewModelInject constructor(private val postsRepository: PostsRepository) :
    ViewModel() {

    private val _postsLiveData = MutableLiveData<State<List<Post>>>()
    private val _postsSearchLiveData = MutableLiveData<List<Post>>()

    val postsLiveData: LiveData<State<List<Post>>>
        get() = _postsLiveData

    val postsSearchLiveData: LiveData<List<Post>>
        get() = _postsSearchLiveData


    fun getPosts() {
        viewModelScope.launch {
            postsRepository.getAllPosts().collect {
                _postsLiveData.value = it
            }
        }
    }

    fun getPostsBySearchVal(searchText:String) {
        val searchText = "%${searchText}%"
        viewModelScope.launch {
            postsRepository.getPostBySearchVal(searchText).collect {
                _postsSearchLiveData.value = it
            }
        }
    }

}