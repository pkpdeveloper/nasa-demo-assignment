package com.nasa.demo.assignment.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nasa.demo.assignment.api.response.ImageResponse
import com.nasa.demo.assignment.database.dao.ImageDao

@Database(entities = [ImageResponse::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun imageDao(): ImageDao
}