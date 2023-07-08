package com.example.bookingappkotlinver3.di

import com.example.bookingappkotlinver3.api.ApiRequest
import com.example.bookingappkotlinver3.repository.dataSource.ApiRequestRemoteDataSource
import com.example.bookingappkotlinver3.repository.dataSourceImpl.BookingRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RemoteDataModule {
    @Singleton
    @Provides
    fun providesBookingRemoteDataSource(apiRequest: ApiRequest): ApiRequestRemoteDataSource {
        return BookingRemoteDataSourceImpl(apiRequest)
    }
}