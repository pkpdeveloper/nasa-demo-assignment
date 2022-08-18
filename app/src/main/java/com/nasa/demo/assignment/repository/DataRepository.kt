package com.nasa.demo.assignment.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nasa.demo.assignment.api.ApiService
import com.nasa.demo.assignment.api.response.ImageResponse
import com.nasa.demo.assignment.const.AppConstants
import com.nasa.demo.assignment.database.AppDatabase
import io.reactivex.MaybeObserver
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class DataRepository constructor(
    private val service: ApiService,
    private val database: AppDatabase
) {
    val imagesLiveData = MutableLiveData<ImageResponse?>()

    fun getImage(date: String): LiveData<ImageResponse?> {
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
                    getFromDatabase(date)
                }

            })
        return imagesLiveData
    }

    private fun getFromDatabase(date: String) {
        database.imageDao().getByDate(date).subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe(object : MaybeObserver<ImageResponse> {
                override fun onSubscribe(d: Disposable) {

                }

                override fun onSuccess(imageResponse: ImageResponse) {
                    imagesLiveData.postValue(imageResponse)
                }

                override fun onError(e: Throwable) {
                    imagesLiveData.postValue(null)
                }

                override fun onComplete() {
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