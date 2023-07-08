package com.example.bookingappkotlinver3.repository

import com.example.bookingappkotlinver3.constant.Resource
import com.example.bookingappkotlinver3.repository.dataSource.ApiRequestRemoteDataSource
import com.example.bookingappkotlinver3.model.request.LocationNearByRequest
import com.example.bookingappkotlinver3.model.response.HotelResponse
import com.example.bookingappkotlinver3.model.response.HotelResponseNearBy
import retrofit2.Response

class BookingRepositoryImpl(private val apiRequestRemoteDataSource: ApiRequestRemoteDataSource) :
    BookingRepository {
    override suspend fun getListHotelNearBy(locationNearByRequest: LocationNearByRequest): Resource<HotelResponseNearBy> =
        responseToResource(apiRequestRemoteDataSource.getListHotelNearBy(locationNearByRequest))

    override suspend fun getAllListHotel(): Resource<HotelResponse> =
        responseToResource(apiRequestRemoteDataSource.getAllListHotel())

    private fun <T> responseToResource(response: Response<T>): Resource<T> {
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }
}