package com.example.bookingappkotlinver3.model.response

import com.example.bookingappkotlinver3.model.Hotel

data class HotelResponseNearBy(
    val message: String,
    val data: ArrayList<Hotel>
)