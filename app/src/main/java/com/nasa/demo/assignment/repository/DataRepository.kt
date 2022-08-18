package com.nasa.demo.assignment.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nasa.demo.assignment.api.ApiService
import com.nasa.demo.assignment.api.response.ImageResponse
import com.nasa.demo.assignment.const.AppConstants
import com.nasa.demo.assignment.database.AppDatabase
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class DataRepository constructor(
    private val service: ApiService,
    private val database: AppDatabase
) {
    private val imagesLiveData = MutableLiveData<ImageResponse?>()
    private val favoriteImagesLiveData = MutableLiveData<List<ImageResponse>>()

    fun getImage(date: String): LiveData<ImageResponse?> {
        database.imageDao().getByDate(date).subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .doOnSuccess {
                imagesLiveData.postValue(it)
            }
            .doOnComplete {
                fetchFromNetwork(date)
            }
            .doOnError {
                fetchFromNetwork(date)
            }.subscribe()

        return imagesLiveData
    }

    fun getFavoriteList(): LiveData<List<ImageResponse>> {
        database.imageDao().getFavoriteList().subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .doOnSuccess {
                favoriteImagesLiveData.postValue(it)
            }
            .doOnError {
                favoriteImagesLiveData.postValue(emptyList())
            }
            .subscribe()

        return favoriteImagesLiveData
    }

    fun updateFavoriteStatus(imageResponse: ImageResponse) {
        database.imageDao().insert(imageResponse).subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io()).subscribe()
    }

    private fun fetchFromNetwork(date: String) {
        service.picsOfTheDay(AppConstants.API_KEY, date)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe(object : SingleObserver<ImageResponse> {

                override fun onSubscribe(d: Disposable) {
                }

                override fun onSuccess(imageResponse: ImageResponse) {
                    imagesLiveData.postValue(imageResponse)
                    insertToDataBase(imageResponse)
                }

                override fun onError(e: Throwable) {
                    imagesLiveData.postValue(null)
                }

            })
    }

    private fun insertToDataBase(imageResponse: ImageResponse) {
        database.imageDao().insert(imageResponse)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe()
    }
}