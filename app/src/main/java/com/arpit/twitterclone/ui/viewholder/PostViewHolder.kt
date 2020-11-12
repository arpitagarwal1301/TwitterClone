package com.arpit.twitterclone.ui.viewholder

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.arpit.twitterclone.R
import com.arpit.twitterclone.databinding.ItemPostBinding
import com.arpit.twitterclone.model.Post
import com.bumptech.glide.Glide


/**
 * Post view holder
 * input -  binding
 */
class PostViewHolder(private val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(post: Post, onItemClicked: (Post, ImageView) -> Unit) {
        binding.name.text = post.name
        binding.handle.text = post.handle
        binding.likeCount.text = post.favoriteCount.toString()
        binding.retweetCount.text = post.retweetCount.toString()
        binding.description.text = post.text


        Glide.with(binding.imageView.context)
            .load(post.profileImageUrl)
            .circleCrop()
            .placeholder(R.drawable.ic_person_24dp)
            .error(R.drawable.ic_person_24dp)
            .into(binding.imageView)

        binding.root.setOnClickListener {
            onItemClicked(post, binding.imageView)
        }
    }
}
