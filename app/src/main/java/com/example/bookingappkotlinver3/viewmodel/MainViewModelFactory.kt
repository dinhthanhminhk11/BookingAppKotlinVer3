package com.example.bookingappkotlinver3.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bookingappkotlinver3.usercase.HomeBookingUseCase

class MainViewModelFactory(
    private val application: Application,
    private val homeBookingUseCase: HomeBookingUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(application, homeBookingUseCase) as T
    }
}