package com.example.bookingappkotlinver3.repository.dataSourceImpl

import com.example.bookingappkotlinver3.api.ApiRequest
import com.example.bookingappkotlinver3.repository.dataSource.ApiRequestRemoteDataSource
import com.example.bookingappkotlinver3.model.request.LocationNearByRequest
import com.example.bookingappkotlinver3.model.response.HotelResponse
import com.example.bookingappkotlinver3.model.response.HotelResponseNearBy
import retrofit2.Response

class BookingRemoteDataSourceImpl(private val apiRequest: ApiRequest) : ApiRequestRemoteDataSource {
    override suspend fun getListHotelNearBy(locationNearByRequest: LocationNearByRequest): Response<HotelResponseNearBy> {
        return apiRequest.getListHotelNearBy(locationNearByRequest)
    }

    override suspend fun getAllListHotel(): Response<HotelResponse> {
        return apiRequest.getAllListHotel()
    }

}