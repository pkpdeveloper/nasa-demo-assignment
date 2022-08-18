package com.nasa.demo.assignment.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.nasa.demo.assignment.api.response.ImageResponse
import com.nasa.demo.assignment.databinding.FavoriteListItemBinding
import com.nasa.demo.assignment.utils.DateUtils


class FavoriteListAdapter(private val listener: FavoriteListCallbackListener?) :
    ListAdapter<ImageResponse, FavoriteListAdapter.ImageViewHolder>(DiffCallback()) {

    inner class ImageViewHolder(private val binding: FavoriteListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var factory = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()

        fun bind(imageResponse: ImageResponse) {
            with(binding) {
                titleTextView.text = imageResponse.title ?: ""
                dateTextView.text = DateUtils.formatDate(imageResponse.date) ?: ""
                Glide.with(imageView).load(imageResponse.url)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .transition(withCrossFade(factory))
                    .into(imageView)
                deleteImageView.setOnClickListener {
                    listener?.onDeleteFavoriteItem(imageResponse)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            FavoriteListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    private class DiffCallback : DiffUtil.ItemCallback<ImageResponse>() {

        override fun areItemsTheSame(oldItem: ImageResponse, newItem: ImageResponse) =
            oldItem.url == newItem.url


        override fun areContentsTheSame(oldItem: ImageResponse, newItem: ImageResponse) =
            oldItem.url == newItem.url
    }

}