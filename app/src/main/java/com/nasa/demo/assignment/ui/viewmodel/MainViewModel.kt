package com.nasa.demo.assignment.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.nasa.demo.assignment.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: DataRepository
) : ViewModel() {

}