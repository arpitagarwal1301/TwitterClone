package com.arpit.twitterclone.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.arpit.twitterclone.data.repository.PostsRepository

/**
 * Main view model
 * @property postsRepository
 * @constructor Create empty Main view model
 */
class MainViewModel @ViewModelInject constructor(private val postsRepository: PostsRepository) :
    ViewModel() {


}