package com.arpit.twitterclone.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.arpit.twitterclone.databinding.ItemPostBinding
import com.arpit.twitterclone.model.Post
import com.arpit.twitterclone.ui.viewholder.PostViewHolder

/**
 * Post list adapter
 * input - onItemClicked
 */
class PostListAdapter(
    private val onItemClicked: (Post, ImageView) -> Unit
) : ListAdapter<Post, PostViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : PostViewHolder {

        return PostViewHolder(
            ItemPostBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int){
        holder.bind(getItem(position), onItemClicked)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Post>() {
            override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean =
                oldItem == newItem
        }
    }
}
