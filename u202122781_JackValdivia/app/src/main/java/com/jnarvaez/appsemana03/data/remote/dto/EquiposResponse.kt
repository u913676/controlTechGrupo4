package com.jnarvaez.appsemana03.data.remote.dto

data class EquiposResponse(
    val success: Boolean,
    val data: EquiposDataDto,
    val timestamp: String
)

data class EquiposDataDto(
    val items: List<EquipoDto>,
    val nextKey: String?
)
