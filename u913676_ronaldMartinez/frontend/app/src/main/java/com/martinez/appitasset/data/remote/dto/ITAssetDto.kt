package com.martinez.appitasset.data.remote.dto

data class ITAssetDto(
    val id: String,
    val equipmentType: String,
    val brand: String,
    val model: String,
    val serialNumber: String,
    val assetTag: String,
    val status: String,
    val location: String,
    val asignedUser: String,
    val purchaseDate: String,
    val notes: String,
)
