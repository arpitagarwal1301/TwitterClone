package com.arpit.twitterclone.utils

import android.app.Activity
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

/**
 * Show toast
 * @param message
 * @param duration
 */
fun Activity.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(applicationContext, message, duration).show()
}

/**
 * Get color res
 * @param id
 */
fun Activity.getColorRes(@ColorRes id: Int) = ContextCompat.getColor(applicationContext, id)