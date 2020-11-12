package com.arpit.twitterclone.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

/**
 * BaseActivity
 * VM - ViewModel
 * VB - ViewBinding
 */
abstract class BaseActivity<VM : ViewModel, VB : ViewBinding> : AppCompatActivity() {

    protected abstract val mViewModel: VM

    protected lateinit var mViewBinding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewBinding = getViewBinding()
    }

    /**
     * Get view binding
     * returns VB
     */
    abstract fun getViewBinding(): VB
}
