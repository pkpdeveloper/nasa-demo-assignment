package com.nasa.demo.assignment.ui.adapter

import com.nasa.demo.assignment.api.response.ImageResponse

interface FavoriteListCallbackListener {
    fun onDeleteFavoriteItem(imageResponse: ImageResponse)
}