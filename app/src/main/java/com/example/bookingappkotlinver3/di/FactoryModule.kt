package com.example.bookingappkotlinver3.di

import android.app.Application
import com.example.bookingappkotlinver3.usercase.HomeBookingUseCase
import com.example.bookingappkotlinver3.viewmodel.MainViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {
    @Singleton
    @Provides
    fun providerBookingViewModelFactory(
        application: Application,
        homeBookingUseCase: HomeBookingUseCase
    ): MainViewModelFactory {
        return MainViewModelFactory(
            application,
            homeBookingUseCase
        )
    }
}