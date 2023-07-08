package com.example.bookingappkotlinver3.di

import com.example.bookingappkotlinver3.view.adapter.NearFromYouAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {
    @Singleton
    @Provides
    fun providerNearFromYouAdapter(): NearFromYouAdapter {
        return NearFromYouAdapter()
    }
}