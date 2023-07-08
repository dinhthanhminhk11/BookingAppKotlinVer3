package com.example.bookingappkotlinver3.di

import com.example.bookingappkotlinver3.repository.BookingRepository
import com.example.bookingappkotlinver3.repository.BookingRepositoryImpl
import com.example.bookingappkotlinver3.repository.dataSource.ApiRequestRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideBookingRepository(apiRequestRemoteDataSource: ApiRequestRemoteDataSource): BookingRepository {
        return BookingRepositoryImpl(
            apiRequestRemoteDataSource
        )
    }
}