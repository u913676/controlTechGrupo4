package com.martinez.appitasset.data.remote.dto

data class ApiResponse(
    val success : Boolean,
    val data : ITAssetsDataDto,
    val timestamp : String
)
