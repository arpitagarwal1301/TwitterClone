package com.arpit.twitterclone.ui

import androidx.activity.viewModels
import com.arpit.twitterclone.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: BaseActivity<MainViewModel, ActivityMainBinding>() {


    override val mViewModel: MainViewModel by viewModels()
    override fun getViewBinding(): ActivityMainBinding {
        TODO("Not yet implemented")
    }


}