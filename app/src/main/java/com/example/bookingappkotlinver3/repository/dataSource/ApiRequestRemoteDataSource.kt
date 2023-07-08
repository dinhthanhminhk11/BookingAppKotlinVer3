package com.example.bookingappkotlinver3.repository.dataSource

import com.example.bookingappkotlinver3.model.request.LocationNearByRequest
import com.example.bookingappkotlinver3.model.response.HotelResponse
import com.example.bookingappkotlinver3.model.response.HotelResponseNearBy
import retrofit2.Response

interface ApiRequestRemoteDataSource {
    suspend fun getListHotelNearBy(locationNearByRequest: LocationNearByRequest): Response<HotelResponseNearBy>
    suspend fun getAllListHotel(): Response<HotelResponse>
}