package com.nasa.demo.assignment.di

import android.content.Context
import androidx.room.Room
import com.nasa.demo.assignment.api.ApiService
import com.nasa.demo.assignment.database.AppDatabase
import com.nasa.demo.assignment.repository.DataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java, "nasa-demo"
        ).build()
    }


    @Provides
    fun provideDataRepository(apiService: ApiService, database: AppDatabase): DataRepository {
        return DataRepository(apiService, database)
    }
}