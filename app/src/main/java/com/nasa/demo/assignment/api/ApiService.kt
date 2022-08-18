package com.nasa.demo.assignment.api

import com.nasa.demo.assignment.api.response.ImageResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("planetary/apod")
    fun picsOfTheDay(
        @Query("api_key") apiKey: String,
        @Query("date") date: String
    ): Single<ImageResponse>
}