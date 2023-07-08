package com.example.bookingappkotlinver3.model.response

import com.example.bookingappkotlinver3.model.Hotel

data class HotelResponse(
    val message: String, val datapros: List<Hotel>
)