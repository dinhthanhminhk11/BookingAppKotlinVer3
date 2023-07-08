package com.example.bookingappkotlinver3.usercase

import com.example.bookingappkotlinver3.constant.Resource
import com.example.bookingappkotlinver3.repository.BookingRepository
import com.example.bookingappkotlinver3.model.request.LocationNearByRequest
import com.example.bookingappkotlinver3.model.response.HotelResponse
import com.example.bookingappkotlinver3.model.response.HotelResponseNearBy

class HomeBookingUseCase(private val bookingRepository: BookingRepository) {
    suspend fun execute(locationNearByRequest: LocationNearByRequest): Resource<HotelResponseNearBy> {
        return bookingRepository.getListHotelNearBy(locationNearByRequest)
    }

    suspend fun executeListAllHotel(): Resource<HotelResponse> {
        return bookingRepository.getAllListHotel()
    }
}