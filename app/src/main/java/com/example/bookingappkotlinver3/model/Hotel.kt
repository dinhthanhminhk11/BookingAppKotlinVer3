package com.example.bookingappkotlinver3.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Hotel(
    val location: Location,
    val _id: String,
    val idUser: String,
    val name: String,
    val images: ArrayList<String>,
    val dienTich: String,
    val tinh: String,
    val huyen: String,
    val xa: String,
    val sonha: String,
    val timeDat: String,
    val timeTra: String,
    @SerializedName("TienNghiKS") val tienNghiKS: ArrayList<TienNghiK>,
    val TbSao: Double,
    val imageConfirm: ArrayList<String>,
    val mota: String,
    val chinhsach: String,
    val yte: Boolean,
    val treEm: Int,
    val chinhSachHuy: Boolean,
    val checkConfirm: Boolean,
    val __v: Int,
    val giaDaoDong: String,
    val calculated: Double
): Serializable