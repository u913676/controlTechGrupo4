package com.martinez.appitasset.domain.model

data class ITAssetModel(
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
