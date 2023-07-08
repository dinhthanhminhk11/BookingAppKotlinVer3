package com.example.bookingappkotlinver3.repository

import com.example.bookingappkotlinver3.constant.Resource
import com.example.bookingappkotlinver3.model.request.LocationNearByRequest
import com.example.bookingappkotlinver3.model.response.HotelResponse
import com.example.bookingappkotlinver3.model.response.HotelResponseNearBy
import retrofit2.Response

interface BookingRepository {
    suspend fun getListHotelNearBy(locationNearByRequest: LocationNearByRequest): Resource<HotelResponseNearBy>
    suspend fun getAllListHotel(): Resource<HotelResponse>
}