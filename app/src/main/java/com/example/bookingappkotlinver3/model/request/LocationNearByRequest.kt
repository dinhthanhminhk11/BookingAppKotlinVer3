package com.example.bookingappkotlinver3.model.request


data class LocationNearByRequest(
    val longitude: Double,
    val latitude: Double,
    val dist: Int,
    private val treEm: Int = 0
)