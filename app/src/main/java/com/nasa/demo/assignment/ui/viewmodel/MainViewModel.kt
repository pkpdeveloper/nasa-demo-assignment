package com.nasa.demo.assignment.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nasa.demo.assignment.api.response.ImageResponse
import com.nasa.demo.assignment.repository.DataRepository
import com.nasa.demo.assignment.utils.DateUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: DataRepository
) : ViewModel() {
    private var selectedDateLiveData = MutableLiveData<String>()

    fun getImage(): LiveData<ImageResponse?> {
        return repository.getImage(getSelectedDate())
    }

    private fun getSelectedDate(): String {
        return selectedDateLiveData.value ?: DateUtils.getToday()
    }

    fun setSelectedDate(dateString: String) {
        selectedDateLiveData.value = dateString
        getImage()
    }

}