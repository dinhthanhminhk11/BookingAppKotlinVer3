package com.example.bookingappkotlinver3.api

import com.example.bookingappkotlinver3.model.request.LocationNearByRequest
import com.example.bookingappkotlinver3.model.response.HotelResponse
import com.example.bookingappkotlinver3.model.response.HotelResponseNearBy
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiRequest {
    @POST("hotelNearBy")
    suspend fun getListHotelNearBy(@Body locationNearByRequest: LocationNearByRequest): Response<HotelResponseNearBy>

    @GET("getAllHotelConfirm")
    suspend fun getAllListHotel(): Response<HotelResponse>
}