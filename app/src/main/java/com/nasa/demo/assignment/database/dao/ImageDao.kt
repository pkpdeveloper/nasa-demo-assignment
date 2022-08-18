package com.nasa.demo.assignment.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nasa.demo.assignment.api.response.ImageResponse
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface ImageDao {

    @Query("SELECT * FROM imageresponse WHERE isFavorite = 1")
    fun getFavoriteList(): Single<List<ImageResponse>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(imageResponse: ImageResponse): Completable

    @Query("SELECT * FROM imageresponse WHERE date = :date")
    fun getByDate(date: String): Maybe<ImageResponse>

}