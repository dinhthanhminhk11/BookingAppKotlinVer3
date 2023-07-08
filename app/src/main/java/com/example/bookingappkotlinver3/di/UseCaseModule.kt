package com.example.bookingappkotlinver3.di

import com.example.bookingappkotlinver3.repository.BookingRepository
import com.example.bookingappkotlinver3.usercase.HomeBookingUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Singleton
    @Provides
    fun provideHomeUseCase(bookingRepository: BookingRepository): HomeBookingUseCase {
        return HomeBookingUseCase(bookingRepository)
    }
}