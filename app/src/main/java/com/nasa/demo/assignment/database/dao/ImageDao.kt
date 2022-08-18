package com.nasa.demo.assignment.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nasa.demo.assignment.api.response.ImageResponse
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface ImageDao {

    @Query("SELECT * FROM imageresponse")
    fun getAll(): Single<List<ImageResponse>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(imageList: List<ImageResponse>): Completable

}